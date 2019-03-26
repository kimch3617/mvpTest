package com.example.mvptest.repository.remote

import com.example.mvptest.data.SearchUsers
import io.reactivex.Observable
import retrofit2.http.*

interface RxRestApi {
    @GET("search/users")
    fun getUsers(@Query("q") query: String, @Query("page") page: Int): Observable<SearchUsers>
}