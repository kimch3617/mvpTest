package com.example.mvptest.di

import com.example.mvptest.BaseApp
//import com.example.mvptest.ui.search.SearchUserModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//@Singleton
//@Component(
//        modules = [
//            AndroidInjectionModule::class,
//            AndroidSupportInjectionModule::class,
//            AppModule::class,
//            SearchUserModule::class
//        ]
//)
//interface AppComponent : AndroidInjector<BaseApp> {
//    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<BaseApp>()
//}