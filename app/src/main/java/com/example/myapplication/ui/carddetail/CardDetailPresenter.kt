package com.example.myapplication.ui.carddetail

import com.example.myapplication.base.BasePresenter
import com.example.myapplication.model.data.card.CardDetail
import com.example.myapplication.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardDetailPresenter : BasePresenter<CardDetailView>() {
    fun getCardDetail(id: Int) {
        RemoteRepository.create().getCardDetail(id).enqueue(object : Callback<CardDetail> {
            override fun onResponse(call: Call<CardDetail>, response: Response<CardDetail>) {
                response.body()?.let { cardDetail ->
                    getView()?.setCard(cardDetail.card)
                    getView()?.setUser(cardDetail.user)
                    getView()?.setRecommendCards(cardDetail.recommendCards)
                }
            }

            override fun onFailure(call: Call<CardDetail>, t: Throwable) {
            }
        })
    }
}