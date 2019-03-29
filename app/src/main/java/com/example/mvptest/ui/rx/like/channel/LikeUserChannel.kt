package com.example.mvptest.ui.rx.like.channel

import com.example.mvptest.data.CallResponse
import com.example.mvptest.ui.rx.ActivityLifecycle
import com.example.mvptest.ui.rx.like.navigation.LikeUserViewAction
import com.example.mvptest.ui.rx.search.channel.SearchUserChannelApi
import com.example.mvptest.ui.rx.search.dto.SearchUserViewAction
import com.example.mvptest.ui.rx.search.navigation.SearchUserNavigation
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import javax.inject.Inject

internal class LikeUserChannel @Inject constructor() : LikeUserChannelApi {

    private val dataChannel: Relay<CallResponse>                = PublishRelay.create()
    private val lifecycleChannel: Relay<ActivityLifecycle>      = PublishRelay.create()
    private val viewActionChannel: Relay<LikeUserViewAction>    = PublishRelay.create()
//    private val navigationChannel: Relay<SearchUserNavigation> = PublishRelay.create()

    override fun ofData(): Observable<CallResponse>             = dataChannel
    override fun ofLifecycle(): Observable<ActivityLifecycle>   = lifecycleChannel
    override fun ofViewAction(): Observable<LikeUserViewAction> = viewActionChannel
//    override fun ofNavigation(): Observable<SearchUserNavigation> = navigationChannel

    override fun accept(data: CallResponse)                 = dataChannel.accept(data)
    override fun accept(lifecycle: ActivityLifecycle)       = lifecycleChannel.accept(lifecycle)
    override fun accept(viewAction: LikeUserViewAction)     = viewActionChannel.accept(viewAction)
//    override fun accept(navigation: SearchUserNavigation)   = navigationChannel.accept(navigation)

}