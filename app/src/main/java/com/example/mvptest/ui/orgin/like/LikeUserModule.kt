package com.example.mvptest.ui.like

import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface LikeUserModule {

    @Binds
    @ActivityScope
    fun providesPresenter(presenter: LikeUserPresenter): LikeUserContract.Presenter

    @Binds
    @ActivityScope
    fun providesAdapter(adapter: LikeUserAdapter): BaseRecyclerViewAdapter<User, LikeUserAdapter.UserHolder>
}