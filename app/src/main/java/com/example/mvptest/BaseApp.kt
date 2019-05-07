package com.example.mvptest

import com.crashlytics.android.Crashlytics
import com.example.mvptest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.fabric.sdk.android.Fabric

class BaseApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val injector = DaggerAppComponent.builder()
            .application(this)
            .build()

        injector.inject(this)

        return injector
    }
}