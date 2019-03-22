package com.example.mvptest.di

import com.example.mvptest.ui.like.LikeUserActivity
import com.example.mvptest.ui.like.LikeUserModule
import com.example.mvptest.ui.search.SearchUserActivity
import com.example.mvptest.ui.search.SearchUserModule
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ActivityInjectionModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchUserModule::class])
    fun serchUserActivity(): SearchUserActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [LikeUserModule::class])
    fun likeUserActivity(): LikeUserActivity
}