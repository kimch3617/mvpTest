package com.example.myapplication.model.data.card

import com.example.myapplication.model.RestResponse

data class CardDetail(val card: Card, val user: User,val recommendCards: ArrayList<Card>) : RestResponse()