package com.example.mvptest.ui.rx.search.dto

import com.example.mvptest.data.User

internal sealed class SearchUserLooknFeel {
    class SetSearchUser(val users: ArrayList<User>) : SearchUserLooknFeel()

    class ClickLikeUser(val position: Int, val isLike: Boolean) : SearchUserLooknFeel()
}