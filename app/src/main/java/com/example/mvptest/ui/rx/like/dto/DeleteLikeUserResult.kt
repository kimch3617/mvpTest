package com.example.mvptest.ui.rx.like.dto

import com.example.mvptest.data.RemoteData
import com.example.mvptest.data.User

internal data class DeleteLikeUserResult(val position: Int, val user: User) : RemoteData