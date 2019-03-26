package com.example.mvptest.ui.rx.search.channel

import com.example.mvptest.data.RemoteData
import com.example.mvptest.ui.rx.ActivityLifecycle
import com.example.mvptest.ui.rx.search.dto.SearchUserViewAction
import com.example.mvptest.ui.rx.search.navigation.SearchUserNavigation
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

internal class SearchUserChannel : SearchUserChannelApi {

    private val dataChannel: Relay<RemoteData>                  = PublishRelay.create()
    private val lifecycleChannel: Relay<ActivityLifecycle>      = PublishRelay.create()
    private val viewActionChannel: Relay<SearchUserViewAction>  = PublishRelay.create()
    private val navigationChannel: Relay<SearchUserNavigation>  = PublishRelay.create()

    override fun ofData(): Observable<RemoteData>                   = dataChannel
    override fun ofLifecycle(): Observable<ActivityLifecycle>       = lifecycleChannel
    override fun ofViewAction(): Observable<SearchUserViewAction>   = viewActionChannel
    override fun ofNavigation(): Observable<SearchUserNavigation>   = navigationChannel

    override fun accept(data: RemoteData)                   = dataChannel.accept(data)
    override fun accept(lifecycle: ActivityLifecycle)       = lifecycleChannel.accept(lifecycle)
    override fun accept(viewAction: SearchUserViewAction)   = viewActionChannel.accept(viewAction)
    override fun accept(navigation: SearchUserNavigation)   = navigationChannel.accept(navigation)

}