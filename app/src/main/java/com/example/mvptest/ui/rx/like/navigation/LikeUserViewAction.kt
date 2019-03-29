package com.example.mvptest.ui.rx.like.navigation

import com.example.mvptest.data.User

internal sealed class LikeUserViewAction {
    class OnLikeDeleted(val position: Int, val user: User) : LikeUserViewAction()
}