package com.example.mvptest.ui.rx.search.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvptest.base.BaseViewModel
import com.example.mvptest.data.CallResponse
import com.example.mvptest.data.SearchUsers
import com.example.mvptest.ui.rx.search.channel.SearchUserChannelApi
import com.example.mvptest.ui.rx.search.dto.LikeUserResult
import com.example.mvptest.ui.rx.search.dto.SearchUserViewAction
import com.example.mvptest.ui.rx.search.repository.SearchUserRepositoryApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

internal class SearchUserViewModel @Inject constructor(
    val channel: SearchUserChannelApi,
    val repository: SearchUserRepositoryApi

): BaseViewModel() {

    val searchDataResponse = MutableLiveData<CallResponse>()
    val likeData = MutableLiveData<CallResponse>()

    val clickSearch = channel.ofViewAction().ofType<SearchUserViewAction.OnSearchClicked>()

    val clickLike = channel.ofViewAction().ofType<SearchUserViewAction.OnLikeClicked>()

    var searchPage = 0

    private val searchUserResponse = channel.ofData().ofType<CallResponse>()

    private val searchUserLoading = channel.ofData().ofType<CallResponse.Loading>()

    private val searchUserSuccess = channel.ofData().ofType<CallResponse.Success<SearchUsers>>()

    private val searchUserError = channel.ofData().ofType<CallResponse.Error>() /** 에러 call 중복 */

    private val likeUserSuccess = channel.ofData().ofType<CallResponse.Success<LikeUserResult>>()

    init {
        Log.e("SearchUserViewModel", "init $channel, $repository")

        repository.setViewModel(this)

        disposable.addAll(*viewDisposables())
    }

    private fun viewDisposables(): Array<Disposable> {
//        return arrayOf(
//            searchUserResponse
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { searchDataResponse.value = it }
//        )
        return arrayOf(
            Observable.merge(searchUserSuccess, searchUserError)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { searchDataResponse.value = it },

            likeUserSuccess
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { likeData.value = it }
        )
    }

}