package com.example.mvptest.di

import android.app.Application
import com.example.mvptest.BaseApp
import com.example.mvptest.repository.remote.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            ActivityInjectionModule::class,
            AppModule::class,
            RemoteModule::class
        ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: BaseApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}