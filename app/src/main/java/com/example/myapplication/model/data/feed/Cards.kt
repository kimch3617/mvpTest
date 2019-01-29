package com.example.myapplication.model.data.feed

import com.example.myapplication.model.RestResponse
import com.example.myapplication.model.data.card.Card

data class Cards(val cards: ArrayList<Card>) : RestResponse()