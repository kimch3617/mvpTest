package com.example.homework

import android.util.Log
import com.example.homework.repository.RemoteRepository
import com.example.homework.base.BasePresenter
import com.example.homework.model.Repos
import com.example.homework.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposPresenter : BasePresenter<ReposView>() {
    fun getUsername(username: String) {
        RemoteRepository.create().getUsername(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let { user ->
                    Log.e("user", user.toString())
                    getView()?.setUserProfile(user)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                getView()?.showToast(t.localizedMessage)
            }
        })
    }

    fun getRepos(username: String) {
        RemoteRepository.create().getRepos(username).enqueue(object : Callback<ArrayList<Repos>> {
            override fun onResponse(call: Call<ArrayList<Repos>>, response: Response<ArrayList<Repos>>) {
                response.body()?.let { reposList ->
                    Log.e("repos", reposList.toString())
                    getView()?.setUserRepos(reposList)
                }
            }

            override fun onFailure(call: Call<ArrayList<Repos>>, t: Throwable) {
                getView()?.showToast(t.localizedMessage)
            }
        })
    }
}