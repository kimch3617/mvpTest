package com.example.mvptest.ui.rx.search.repository

import com.example.mvptest.ui.rx.search.viewModel.SearchUserViewModel

internal interface SearchUserRepositoryApi {
    fun setViewModel(viewModel: SearchUserViewModel)
}