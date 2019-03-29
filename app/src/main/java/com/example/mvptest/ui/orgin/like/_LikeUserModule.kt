package com.example.mvptest.ui.like

import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.data.User
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

@Module
internal interface _LikeUserModule {

    @Binds
    @ActivityScope
    fun providesPresenter(presenter: LikeUserPresenter): LikeUserContract.Presenter

    @Binds
    @ActivityScope
    fun providesAdapter(adapter: _LikeUserAdapter): BaseRecyclerViewAdapter<User, _LikeUserAdapter.UserHolder>
}