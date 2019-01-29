package com.example.myapplication.model.data.home

import com.example.myapplication.model.RestResponse
import com.example.myapplication.model.data.card.Card
import com.example.myapplication.model.data.card.User

data class HomeData(val popularCards: ArrayList<Card>, val popularUsers: ArrayList<User>) : RestResponse()