package com.example.mvptest.repository.remote

import com.example.mvptest.data.SearchUsers
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @GET("search/users")
    fun getUsers(@Query("q") query: String, @Query("page") page: Int): Call<SearchUsers>
}