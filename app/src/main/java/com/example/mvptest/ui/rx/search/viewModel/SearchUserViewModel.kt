package com.example.mvptest.ui.rx.search.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvptest.data.User
import com.example.mvptest.ui.rx.search.channel.SearchUserChannelApi
import com.example.mvptest.ui.rx.search.dto.SearchUserViewAction
import com.example.mvptest.ui.rx.search.repository.SearchUserRepositoryApi
import io.reactivex.rxkotlin.ofType
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class SearchUserViewModel @Inject constructor(
    private val channel: SearchUserChannelApi,
    private val repository: SearchUserRepositoryApi

): ViewModel() {

    val searchData = MutableLiveData<ArrayList<User>>()

    val clickSearch = channel.ofViewAction().ofType<SearchUserViewAction.OnSearchClicked>().throttleFirst(500, TimeUnit.MILLISECONDS)

    val clickLike = channel.ofViewAction().ofType<SearchUserViewAction.OnLikeClicked>()

    init {
        Log.e("SearchUserViewModel", "init")

    }

}