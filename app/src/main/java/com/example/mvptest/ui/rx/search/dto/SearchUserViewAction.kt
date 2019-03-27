package com.example.mvptest.ui.rx.search.dto

internal sealed class SearchUserViewAction {
    class OnSearchClicked(val query: String) : SearchUserViewAction()

    object OnLikeClicked : SearchUserViewAction()
}