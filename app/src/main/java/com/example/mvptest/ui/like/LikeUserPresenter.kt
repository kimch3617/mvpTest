package com.example.mvptest.ui.like

import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource

class LikeUserPresenter(private val dataSource: UserLocalDataSource?) : LikeUserContract.Presenter() {
    override fun loadLikeUser() {
        dataSource?.getUsers { users ->
            getView()?.addUsers(users)
        }
    }

    override fun removeLikeUser(user: User) {
        getView()?.postEventUser(user)
        getView()?.removeUser(user)
        dataSource?.deleteUser(user)
    }
}