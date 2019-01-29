package com.example.myapplication.repository

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository {
    companion object {
        val BASE_URL = "http://35.200.92.60:11000"
        val get: RestApi by lazy { Create.INSTANCE.service }

        fun create(): RestApi {
            return Create.INSTANCE.service
        }
    }

    var service: RestApi
    private object Create { val INSTANCE = RemoteRepository() }

    init {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))

        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)

        retrofit.client(httpClient.build())

        service = retrofit.build().create(RestApi::class.java)
    }
}