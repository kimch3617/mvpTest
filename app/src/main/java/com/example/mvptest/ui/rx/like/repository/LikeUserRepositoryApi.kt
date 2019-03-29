package com.example.mvptest.ui.rx.like.repository

import com.example.mvptest.ui.rx.like.viewModel.LikeUserViewModel

internal interface LikeUserRepositoryApi {
    fun setViewModel(viewModel: LikeUserViewModel)
}