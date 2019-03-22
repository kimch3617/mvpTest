package com.example.mvptest

import com.example.mvptest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val injector = DaggerAppComponent.builder()
            .application(this)
            .build()

        injector.inject(this)

        return injector
    }
}