package com.example.mvptest.ui.like

import com.example.mvptest.base.BasePresenter
import com.example.mvptest.base.BaseView
import com.example.mvptest.data.User

interface LikeUserContract {
    interface View : BaseView {
        fun addUsers(users: List<User>)
        fun removeUser(user: User)
        fun postEventUser(user: User)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun loadLikeUser()
        abstract fun removeLikeUser(user: User)
    }
}
