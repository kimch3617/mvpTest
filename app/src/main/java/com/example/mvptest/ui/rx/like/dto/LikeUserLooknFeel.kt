package com.example.mvptest.ui.rx.like.dto

import com.example.mvptest.data.User

internal sealed class LikeUserLooknFeel {
    class LikeUsers(val users: List<User>?) : LikeUserLooknFeel()

    class DeleteLikeUser(val user: User?) : LikeUserLooknFeel()
}