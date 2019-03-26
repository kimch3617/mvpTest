package com.example.mvptest.repository.remote

import android.util.Log
import com.example.mvptest.di.qualifier.BaseUrl
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

internal class RemoteRepository @Inject constructor(@BaseUrl private val baseUrl: String) {

    var service: RestApi

    init {
        Log.e("provideRemoteRepository", "inittest")
        val gsonBuilder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)

        retrofit.client(httpClient.build())

        service = retrofit.build().create(RestApi::class.java)
    }

}