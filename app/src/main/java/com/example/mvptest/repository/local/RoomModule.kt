package com.example.mvptest.repository.local

import android.content.Context
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

//@Module(
//    includes = [
//        RoomModule.ProvideModule::class
//    ]
//)
//internal interface RoomModule {
//    @Module
//    class ProvideModule {
////        @Provides
////        @ActivityScope
////        fun provideDataSource(context: Context): UserLocalDataSource {
////            return UserLocalDataSource(context)
////        }
//    }
//
//    @ActivityScope
//    @Binds
//    fun provideDataSource(context: Context): UserLocalDataSource
//}