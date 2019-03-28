package com.example.mvptest.ui.orgin.search

import android.os.Handler
import android.os.Looper
import com.example.mvptest.data.SearchUsers
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.repository.remote.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchUserPresenter @Inject constructor(
        private val dataSource: UserLocalDataSource?,
        private val remote: RestApi

) : SearchUserContract.Presenter() {

    private var query = ""
    private var page = 1

    override fun loadUsers() {
        getUsers(query, page)
    }

    override fun setUsers(users: SearchUsers) {
        val ids = users.items.map { it.id }

//        dataSource?.containUsers(ids) { list ->
//            val items = users.items.map {  user ->
//                if (list.contains(user)) user.isLike = true
//                user
//            }
//
//            Handler(Looper.getMainLooper()).post {
//                getView()?.addUsers(items)
//                page++
//            }
//        }
    }

    override fun searchQuery(query: String?) {
        this.page  = 1
        this.query = query ?: ""

        getView()?.removeAdapterItems()

        getUsers(this.query, this.page)
    }

    override fun addLikeUser(position: Int, user: User) {
        if (!user.isLike) {
            dataSource?.insertUser(user)
            user.isLike = true
            getView()?.notifyItemChanged(position)
        }
    }

    private fun getUsers(query: String, page: Int) {
        remote.getUsers(query, page).enqueue(object : Callback<SearchUsers> {
            override fun onResponse(call: Call<SearchUsers>, response: Response<SearchUsers>) {
                response.body()?.let { users ->
                    setUsers(users)
                }
            }

            override fun onFailure(call: Call<SearchUsers>, t: Throwable) {
                getView()?.showToast(t.localizedMessage)
            }
        })
    }

}