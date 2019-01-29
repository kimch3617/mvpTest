package com.example.myapplication.ui.feed

import com.example.myapplication.base.BaseView
import com.example.myapplication.model.data.card.Card

interface FeedView : BaseView {
    fun loading()
    fun loaded()
    fun setCards(cards: ArrayList<Card>)
}