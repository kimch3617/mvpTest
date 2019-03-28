package com.example.mvptest.repository.remote

import com.example.mvptest.BuildConfig
import com.example.mvptest.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
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
            return RemoteCall(baseUrl).service
        }

        @Provides
        @Singleton
        fun provideRxRemoteRepository(@BaseUrl baseUrl: String): RxRestApi {
            return RxRemoteCall(baseUrl).service
        }
    }
}