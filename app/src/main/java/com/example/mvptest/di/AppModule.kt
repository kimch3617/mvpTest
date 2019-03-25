package com.example.mvptest.di

import android.app.Application
import android.content.Context
import com.example.mvptest.repository.local.UserLocalDataSource
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(
    includes = [
        AppModule.ProvideModule::class
    ]
)
internal interface AppModule {
    @Module
    class ProvideModule {
        @Provides
        @Singleton
        fun provideDataSource(context: Context): UserLocalDataSource {
            return UserLocalDataSource(context)
        }

        @Provides
        fun provideExecutorService(): ExecutorService {
            return Executors.newCachedThreadPool()
        }
    }

    @Binds
    @Singleton
    fun provideApplicationContext(context: Application): Context
}