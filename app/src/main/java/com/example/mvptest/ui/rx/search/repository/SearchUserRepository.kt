package com.example.mvptest.ui.rx.search.repository

import android.util.Log
import com.example.mvptest.data.CallResponse
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.repository.remote.RxRestApi
import com.example.mvptest.ui.rx.search.dto.LikeUserResult
import com.example.mvptest.ui.rx.search.dto.SearchUserViewAction
import com.example.mvptest.ui.rx.search.viewModel.SearchUserViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class SearchUserRepository @Inject constructor(
    private val apiCall: RxRestApi,
    private val dataSource: UserLocalDataSource

): SearchUserRepositoryApi {

    override fun setViewModel(viewModel: SearchUserViewModel) {
        with(viewModel) {
            val executeSearchUsers = Observable.merge(clickSearch, pagingSearch)
                .observeOn(Schedulers.io())
                .concatMap {
                    if(it is SearchUserViewAction.OnSearchClicked) {
                        queryAndPage.query = it.query
                        queryAndPage.page = 0
                    }
                    if(it is SearchUserViewAction.OnSearchPaging) {
                        queryAndPage.page++
                    }
                    apiCall.getUsers(queryAndPage.query, queryAndPage.page)
                }
                .doOnNext {
                    Log.e("search", "users: $it")
                }
                .map { users ->
                    val ids = users.items.map { it.id }
                    val containUsers = dataSource.containUsers(ids)

                    users.items.map { user ->
                        if (containUsers.contains(user)) {
                            user.isLike = true
                        }
                    }

                    CallResponse.Success(users) as CallResponse
                }
                .onErrorReturn { CallResponse.Error(it) }

            val executeAddLikeUser = clickLike
                .observeOn(Schedulers.io())
                .map {
                    it.user.isLike = true
                    dataSource.insertUser(it.user)
                    CallResponse.Success2(LikeUserResult(it.position, true)) as CallResponse
                }
                .doOnNext {
                    Log.e("add", "users: $it")
                }
                .onErrorReturn { CallResponse.Error(it) }
                .share()

            disposable.addAll(
                executeSearchUsers.subscribe(channel::accept),
                executeAddLikeUser.subscribe(channel::accept)
            )
        }
    }

}