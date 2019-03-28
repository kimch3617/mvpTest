package com.example.mvptest.data

data class SearchUsers(val totalCount: Int, val incompleteResults: Boolean, val items: ArrayList<User>): RemoteData