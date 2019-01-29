package com.example.myapplication.ui.feed

import com.example.myapplication.base.BasePresenter
import com.example.myapplication.model.data.feed.Cards
import com.example.myapplication.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedPresenter : BasePresenter<FeedView>() {
    fun getCards(page: Int) {
        getView()?.loading()
        RemoteRepository.create().getCards(page).enqueue(object : Callback<Cards> {
            override fun onResponse(call: Call<Cards>, response: Response<Cards>) {
                response.body()?.let { cards ->
                    getView()?.setCards(cards.cards)
                }
                getView()?.loaded()
            }

            override fun onFailure(call: Call<Cards>, t: Throwable) {
                getView()?.loaded()
            }
        })
    }
}