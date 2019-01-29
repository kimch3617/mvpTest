package com.example.myapplication.ui.carddetail

import com.example.myapplication.base.BaseView
import com.example.myapplication.model.data.card.Card
import com.example.myapplication.model.data.card.User

interface CardDetailView : BaseView {
    fun setCard(card: Card)
    fun setUser(user: User)
    fun setRecommendCards(cards: ArrayList<Card>)
}