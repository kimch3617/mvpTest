package com.example.mvptest.ui.rx.search.dto

import com.example.mvptest.data.User

internal sealed class SearchUserViewAction {
    class OnSearchClicked(val query: String) : SearchUserViewAction()

    class OnLikeClicked(val position: Int, val user: User) : SearchUserViewAction()
}