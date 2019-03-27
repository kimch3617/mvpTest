package com.example.mvptest.ui.orgin.search

import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.data.User
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        _SearchUserModule.ProvideModule::class
    ]
)
internal interface _SearchUserModule {
    @Module
    class ProvideModule {
//        @Provides
//        @ActivityScope
//        fun providesPresenter(dataSource: UserLocalDataSource): SearchUserPresenter {
//            return SearchUserPresenter(dataSource)
//        }

//        @Provides
//        @ActivityScope
//        fun provideDataSource(context: Context): UserLocalDataSource {
//            return UserLocalDataSource(context)
//        }
    }

    @Binds
    @ActivityScope
    fun providesPresenter(presenter: SearchUserPresenter): SearchUserContract.Presenter

    @Binds
    @ActivityScope
    fun providesAdapter(adapter: _SearchUserAdapter): BaseRecyclerViewAdapter<User, _SearchUserAdapter.UserHolder>
}