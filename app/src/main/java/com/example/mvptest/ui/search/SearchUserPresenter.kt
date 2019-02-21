package com.example.mvptest.ui.search

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.mvptest.base.BaseActivity
import com.example.mvptest.data.SearchUsers
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.repository.remote.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserPresenter(private val context: Context, private val dataSource: UserLocalDataSource?) : SearchUserContract.Presenter() {
    private var mQuery = ""
    private var mPage = 1

    private fun getUsers(query: String, page: Int) {
        RemoteRepository.create().getUsers(query, page).enqueue(object : Callback<SearchUsers> {
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

    override fun loadUsers() {
        getUsers(mQuery, mPage)
    }

    override fun setUsers(users: SearchUsers) {
        val ids = users.items.map { it.id }
        dataSource?.containUsers(ids) { list ->
            val items = users.items.map {  user ->
                if (list.contains(user)) user.isLike = true
                user
            }
            (context as BaseActivity).runOnUiThread {
                getView()?.addUsers(items)
                mPage++
            }
        }
    }

    override fun searchQuery(query: String?) {
        mPage = 1
        mQuery = query ?: ""
        getView()?.removeAdapterItems()
        getUsers(mQuery, mPage)
    }

    override fun addLikeUser(position: Int, user: User) {
        if (!user.isLike) {
            dataSource?.insertUser(user)
            user.isLike = true
            getView()?.notifyItemChanged(position)
        }
    }
}