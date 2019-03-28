package com.example.mvptest.ui.like

import android.util.Log
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import javax.inject.Inject

class LikeUserPresenter @Inject constructor(private val dataSource: UserLocalDataSource?) : LikeUserContract.Presenter() {
    override fun loadLikeUser() {
        Log.e("LikeUserPresenter", "$dataSource")
//        dataSource?.getUsers { users ->
//            getView()?.addUsers(users)
//        }
    }

    override fun removeLikeUser(user: User) {
        getView()?.postEventUser(user)
        getView()?.removeUser(user)
        dataSource?.deleteUser(user)
    }
}