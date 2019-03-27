package com.example.mvptest.ui.rx.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.base.ViewModelProviderFactory
import com.example.mvptest.data.User
import com.example.mvptest.di.qualifier.ViewModelKey
import com.example.mvptest.ui.rx.search.channel.SearchUserChannel
import com.example.mvptest.ui.rx.search.channel.SearchUserChannelApi
import com.example.mvptest.ui.rx.search.repository.SearchUserRepository
import com.example.mvptest.ui.rx.search.repository.SearchUserRepositoryApi
import com.example.mvptest.ui.rx.search.viewModel.SearchUserViewModel
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        SearchUserModule.ProvideModule::class
    ]
)
internal interface SearchUserModule {
    @Module
    class ProvideModule

    @Binds
    @ActivityScope
    fun providesAdapter(adapter: SearchUserAdapter): BaseRecyclerViewAdapter<User, SearchUserAdapter.UserHolder>

    @Binds
    @ActivityScope
    fun provideSearchUserChannel(channel: SearchUserChannel): SearchUserChannelApi

    @Binds
    @ActivityScope
    fun provideSearchUserRepository(repository: SearchUserRepository): SearchUserRepositoryApi

    @Binds
    @ActivityScope
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(SearchUserViewModel::class)
    fun provideSearchUserViewModel(viewModel: SearchUserViewModel): ViewModel
}