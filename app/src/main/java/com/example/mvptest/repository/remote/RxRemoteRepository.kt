package com.example.mvptest.repository.remote

import android.util.Log
import com.example.mvptest.di.qualifier.BaseUrl
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

internal class RxRemoteRepository @Inject constructor(@BaseUrl private val baseUrl: String) {

    var service: RxRestApi

    init {
        Log.e("provideRemoteRepository", "rx-init")
        val gsonBuilder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)

        retrofit.client(httpClient.build())

        service = retrofit.build().create(RxRestApi::class.java)
    }

}