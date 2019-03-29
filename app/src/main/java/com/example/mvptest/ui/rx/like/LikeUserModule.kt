package com.example.mvptest.ui.rx.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.base.ViewModelProviderFactory
import com.example.mvptest.data.User
import com.example.mvptest.di.qualifier.ViewModelKey
import com.example.mvptest.ui.like.LikeUserAdapter
import com.example.mvptest.ui.rx.like.channel.LikeUserChannel
import com.example.mvptest.ui.rx.like.channel.LikeUserChannelApi
import com.example.mvptest.ui.rx.like.repository.LikeUserRepository
import com.example.mvptest.ui.rx.like.repository.LikeUserRepositoryApi
import com.example.mvptest.ui.rx.like.viewModel.LikeUserViewModel
import com.knowre.android.digitalmath.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        LikeUserModule.ProvideModule::class
    ]
)
internal interface LikeUserModule {
    @Module
    class ProvideModule

    @Binds
    @ActivityScope
    fun providesAdapter(adapter: LikeUserAdapter): BaseRecyclerViewAdapter<User, LikeUserAdapter.UserHolder>

    @Binds
    @ActivityScope
    fun provideLikeUserChannel(channel: LikeUserChannel): LikeUserChannelApi

    @Binds
    @ActivityScope
    fun provideLikeUserRepository(repository: LikeUserRepository): LikeUserRepositoryApi

    @Binds
    @ActivityScope
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(LikeUserViewModel::class)
    fun provideLikeUserViewModel(viewModel: LikeUserViewModel): ViewModel
}