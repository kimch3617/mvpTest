package com.example.mvptest.ui.rx.search

import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.data.User
import com.example.mvptest.ui.orgin.search.SearchUserAdapter
import com.example.mvptest.ui.orgin.search.SearchUserContract
import com.example.mvptest.ui.orgin.search.SearchUserPresenter
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

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
    fun providesAdapter(adapter: SearchUserAdapter): BaseRecyclerViewAdapter<User, SearchUserAdapter.UserHolder>
}