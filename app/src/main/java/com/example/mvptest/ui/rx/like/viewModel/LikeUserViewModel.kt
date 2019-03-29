package com.example.mvptest.ui.rx.like.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvptest.base.BaseViewModel
import com.example.mvptest.data.CallResponse
import com.example.mvptest.ui.rx.ActivityLifecycle
import com.example.mvptest.ui.rx.like.channel.LikeUserChannelApi
import com.example.mvptest.ui.rx.like.dto.DeleteLikeUserResult
import com.example.mvptest.ui.rx.like.dto.LikeUserLooknFeel
import com.example.mvptest.ui.rx.like.dto.LikeUsersResult
import com.example.mvptest.ui.rx.like.navigation.LikeUserViewAction
import com.example.mvptest.ui.rx.like.repository.LikeUserRepositoryApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.ofType
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class LikeUserViewModel @Inject constructor(
    val channel: LikeUserChannelApi,
    val repository: LikeUserRepositoryApi

): BaseViewModel() {

    val loadLikeUserResponse = MutableLiveData<LikeUserLooknFeel.LikeUsers>()
    val deleteLikeResponse = MutableLiveData<LikeUserLooknFeel.DeleteLikeUser>()

    val onCreate = channel.ofLifecycle().ofType<ActivityLifecycle.OnCreate>()

    val loadLikeUsers = onCreate

    val clickDeleteLike = channel.ofViewAction().ofType<LikeUserViewAction.OnLikeDeleted>()

    private val loadLikeUserSuccess = channel.ofData().ofType<CallResponse.Success<LikeUsersResult>>()

    private val deleteLikeUserSuccess = channel.ofData().ofType<CallResponse.Success2<DeleteLikeUserResult>>()

//    private val deleteLikeUserError = channel.ofData().ofType<CallResponse.Error>()

    init {
        repository.setViewModel(this)

        disposable.addAll(*viewDisposables())
    }

    private fun viewDisposables(): Array<Disposable> {
        return arrayOf(
            loadLikeUserSuccess
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it.data?.let { result ->
                        loadLikeUserResponse.value = LikeUserLooknFeel.LikeUsers(result.users)
                    }
                },

            deleteLikeUserSuccess
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it.data?.let { result ->
                        deleteLikeResponse.value = LikeUserLooknFeel.DeleteLikeUser(result.user)
                    }
                }
        )
    }

}