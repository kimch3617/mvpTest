package com.example.mvptest.ui.search

import android.content.Context
import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        SearchUserModule.ProvideModule::class
    ]
)
internal interface SearchUserModule {
    @Module
    class ProvideModule {
//        @Provides
//        @ActivityScope
//        fun providesPresenter(dataSource: UserLocalDataSource): SearchUserPresenter {
//            return SearchUserPresenter(dataSource)
//        }

        @Provides
        @ActivityScope
        fun provideDataSource(context: Context): UserLocalDataSource {
            return UserLocalDataSource(context)
        }
    }

    @Binds
    @ActivityScope
    fun providesPresenter(presenter: SearchUserPresenter): SearchUserContract.Presenter

    @Binds
    @ActivityScope
    fun providesAdapter(adapter: SearchUserAdapter): BaseRecyclerViewAdapter<User, SearchUserAdapter.UserHolder>
}