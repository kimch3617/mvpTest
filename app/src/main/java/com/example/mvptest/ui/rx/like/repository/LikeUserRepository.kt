package com.example.mvptest.ui.rx.like.repository

import android.util.Log
import com.example.mvptest.data.CallResponse
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.ui.rx.like.dto.DeleteLikeUserResult
import com.example.mvptest.ui.rx.like.dto.LikeUsersResult
import com.example.mvptest.ui.rx.like.viewModel.LikeUserViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class LikeUserRepository @Inject constructor(private val dataSource: UserLocalDataSource) : LikeUserRepositoryApi {

    override fun setViewModel(viewModel: LikeUserViewModel) {
        with(viewModel) {
            val executeLoadLikeUsers = loadLikeUsers
                .observeOn(Schedulers.io())
                .concatMap { dataSource.getUsers() }
                .map { CallResponse.Success(LikeUsersResult(it)) }
                .doOnNext {
                    Log.e("loadUsers", "${it.data?.users}")
                }
                .share()

            val executeDeleteLike = clickDeleteLike
                .observeOn(Schedulers.io())
                .map {
                    dataSource.deleteUser(it.user)
                    CallResponse.Success2(DeleteLikeUserResult(it.position, it.user))
                }
                .doOnNext {
                    Log.e("deleteUser", "${it.data?.user}")
                }
                .share()

            disposable.addAll(
                executeLoadLikeUsers.subscribe(channel::accept),
                executeDeleteLike.subscribe(channel::accept)
            )
        }
    }

}