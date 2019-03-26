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

        @Provides
        @Singleton
        fun provideRemoteRepository(@BaseUrl baseUrl: String): RestApi {
            return RemoteRepository(baseUrl).service
        }

        @Provides
        @Singleton
        fun provideRxRemoteRepository(@BaseUrl baseUrl: String): RxRestApi {
            return RxRemoteRepository(baseUrl).service
        }
    }
}