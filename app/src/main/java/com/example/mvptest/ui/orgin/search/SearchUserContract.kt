package com.example.mvptest.ui.orgin.search

import com.example.mvptest.base.BasePresenter
import com.example.mvptest.base.BaseView
import com.example.mvptest.data.SearchUsers
import com.example.mvptest.data.User

interface SearchUserContract {
    interface View : BaseView {
        fun addUsers(users: List<User>)
        fun removeAdapterItems()
        fun notifyItemChanged(position: Int)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun loadUsers()
        abstract fun setUsers(users: SearchUsers)
        abstract fun searchQuery(query: String?)
        abstract fun addLikeUser(position: Int, user: User)
    }
}
