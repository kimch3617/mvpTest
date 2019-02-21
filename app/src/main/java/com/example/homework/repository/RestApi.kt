package com.example.homework.repository

import com.example.homework.model.Repos
import com.example.homework.model.User
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @GET("/users/{username}")
    fun getUsername(@Path("username") username: String): Call<User>
    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") username: String): Call<ArrayList<Repos>>
}