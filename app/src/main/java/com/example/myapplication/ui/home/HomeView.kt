package com.example.myapplication.ui.home

import com.example.myapplication.base.BaseView
import com.example.myapplication.model.data.card.Card
import com.example.myapplication.model.data.card.User

interface HomeView : BaseView {
    fun setPopularCards(popularCards: ArrayList<Card>)
    fun setPopularUsers(users: ArrayList<User>)
}