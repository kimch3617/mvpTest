package com.example.myapplication.repository

import com.example.myapplication.model.RestResponse
import com.example.myapplication.model.data.card.CardDetail
import com.example.myapplication.model.data.feed.Cards
import com.example.myapplication.model.data.home.HomeData
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @GET("home")
    fun getHome(): Call<HomeData>
    @GET("cards")
    fun getCards(@Query("page") page: Int, @Query("per") per: Int = 20): Call<Cards>
    @GET("cards/{id}")
    fun getCardDetail(@Path("id") id: Int): Call<CardDetail>

    @POST("users/sign_in")
    @FormUrlEncoded
    fun login(@Field("nickname") nickname: String, @Field("pwd") password: String): Call<RestResponse>
    @POST("users")
    @FormUrlEncoded
    fun register(@Field("nickname") nickname: String, @Field("introduction") introduction: String,
                 @Field("pwd") password: String): Call<RestResponse>
}