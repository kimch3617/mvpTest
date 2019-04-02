package com.example.mvptest.ui.rx.search.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvptest.base.BaseViewModel
import com.example.mvptest.data.CallResponse
import com.example.mvptest.data.SearchUsers
import com.example.mvptest.ui.rx.search.channel.SearchUserChannelApi
import com.example.mvptest.ui.rx.search.dto.LikeUserResult
import com.example.mvptest.ui.rx.search.dto.QueryAndPageRefined
import com.example.mvptest.ui.rx.search.dto.SearchUserLooknFeel
import com.example.mvptest.ui.rx.search.dto.SearchUserViewAction
import com.example.mvptest.ui.rx.search.repository.SearchUserRepositoryApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.ofType
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class SearchUserViewModel @Inject constructor(
    val channel: SearchUserChannelApi,
    val repository: SearchUserRepositoryApi

): BaseViewModel() {

    val searchDataResponse = MutableLiveData<SearchUserLooknFeel.SetSearchUser>()
    val likeDataResponse = MutableLiveData<SearchUserLooknFeel.ClickLikeUser>()

    val clickSearch = channel.ofViewAction().ofType<SearchUserViewAction.OnSearchClicked>()

    val clickLike = channel.ofViewAction().ofType<SearchUserViewAction.OnLikeClicked>().debounce(500L, TimeUnit.MILLISECONDS)

    val pagingSearch = channel.ofViewAction().ofType<SearchUserViewAction.OnSearchPaging>().debounce(500L, TimeUnit.MILLISECONDS)

    var queryAndPage = QueryAndPageRefined()

    private val searchUserSuccess = channel.ofData().ofType<CallResponse.Success<SearchUsers>>()

    private val likeUserSuccess = channel.ofData().ofType<CallResponse.Success2<LikeUserResult>>()

    init {
        Log.e("SearchUserViewModel", "init $disposable")

        repository.setViewModel(this)

        disposable.addAll(*viewDisposables())
    }

    private fun viewDisposables(): Array<Disposable> {
        return arrayOf(
            searchUserSuccess
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it.data?.let { result ->
                        searchDataResponse.value = SearchUserLooknFeel.SetSearchUser(result.items)
                    }
                },

            likeUserSuccess
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it.data?.let { result ->
                        likeDataResponse.value = SearchUserLooknFeel.ClickLikeUser(result.position, true)
                    }
                }
        )
    }

}