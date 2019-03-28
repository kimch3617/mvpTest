package com.example.mvptest.ui.rx.search.repository

import android.util.Log
import com.example.mvptest.data.CallResponse
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.repository.remote.RxRestApi
import com.example.mvptest.ui.rx.search.dto.LikeUserResult
import com.example.mvptest.ui.rx.search.viewModel.SearchUserViewModel
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class SearchUserRepository @Inject constructor(
    private val apiCall: RxRestApi,
    private val dataSource: UserLocalDataSource

): SearchUserRepositoryApi {

    override fun setViewModel(viewModel: SearchUserViewModel) {
        with(viewModel) {
            val executeSearchUsers = clickSearch
                .observeOn(Schedulers.io())
                .concatMap { apiCall.getUsers(it.query, searchPage) }
                .doOnNext {
                    Log.e("search", "users: $it")
                }
                .doOnError { Log.e("check", "err") }
                .doOnComplete { Log.e("search", "complete") }
//                .map { users ->
//                    val ids = users.items.map { it.id }
//                    dataSource.containUsers(ids) { list ->
//                        users.items.map { user ->
//                            if (list.contains(user)) {
//                                user.isLike = true
//                            }
//                        }
//                    }
//
//                    CallResponse.Success(users) as CallResponse
//                }
//                .onErrorReturn { CallResponse.Error(it) }
//                .share()

            val executorCheckLikeUsers = executeSearchUsers
                .observeOn(Schedulers.io())
                .concatMap { users ->
                    val ids = users.items.map { it.id }
                    dataSource.containUsers(ids)
                }
                .doOnNext {
                    Log.e("check", "users: $it")
                }
                .doOnError { Log.e("check", "err") }
                .doOnComplete { Log.e("check", "complete") }
//                .share()

            val executorSearchAndLikeUsers = Observables
                .zip(executeSearchUsers, executorCheckLikeUsers) { searchUsers, likeUsers ->
                    searchUsers.items.map { user ->
                        if (likeUsers.contains(user)) {
                            user.isLike = true
                        }
                    }

                    CallResponse.Success(searchUsers) as CallResponse
                }
                .onErrorReturn { CallResponse.Error(it) }
                .doOnNext {
                    Log.e("search_check", "users: $it")
                }
                .doOnError { Log.e("search_check", "err") }
                .doOnComplete { Log.e("search_check", "complete") }
                .share()

            val executeAddLikeUser = clickLike
                .observeOn(Schedulers.io())
                .map {
                    dataSource.insertUser(it.user)
                    CallResponse.Success(LikeUserResult(it.position, true)) as CallResponse
                }
                .onErrorReturn { CallResponse.Error(it) }
                .share()

            disposable.addAll(
                executorSearchAndLikeUsers.subscribe(channel::accept),
                executeAddLikeUser.subscribe(channel::accept)
            )
        }
    }

}