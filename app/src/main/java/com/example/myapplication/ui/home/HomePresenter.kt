package com.example.myapplication.ui.home

import com.example.myapplication.base.BasePresenter
import com.example.myapplication.model.data.home.HomeData
import com.example.myapplication.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter : BasePresenter<HomeView>() {
    fun getHome() {
        RemoteRepository.create().getHome().enqueue(object : Callback<HomeData> {
            override fun onResponse(call: Call<HomeData>, response: Response<HomeData>) {
                response.body()?.let { home ->
                    getView()?.setPopularCards(home.popularCards)
                    getView()?.setPopularUsers(home.popularUsers)
                }
            }

            override fun onFailure(call: Call<HomeData>, t: Throwable) {
            }
        })
    }
}