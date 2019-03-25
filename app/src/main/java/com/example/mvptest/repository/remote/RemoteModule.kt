package com.example.mvptest.repository.remote

import android.util.Log
import com.example.mvptest.BuildConfig
import com.example.mvptest.di.qualifier.BaseUrl
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(
    includes = [
        RemoteModule.ProvideModule::class
    ]
)
internal interface RemoteModule {
    @Module
    class ProvideModule {
        @Provides
        @Singleton
        @BaseUrl
        fun provideBaseUrl(): String {
            return BuildConfig.BASE_URL
        }

//        @Provides
//        @Singleton
//        fun provideRetrofit(@BaseUrl baseUrl: String): RestApi {
//            val gsonBuilder = GsonBuilder()
//            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//            val retrofit = Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
//
//            val httpClient = OkHttpClient.Builder()
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//            httpClient.addInterceptor(interceptor)
//
//            retrofit.client(httpClient.build())
//
//            return retrofit.build().create(RestApi::class.java)
//        }

        @Provides
        @Singleton
        fun provideRemoteRepository(@BaseUrl baseUrl: String): RestApi {
            return RemoteRepository(baseUrl).service
        }
    }
}