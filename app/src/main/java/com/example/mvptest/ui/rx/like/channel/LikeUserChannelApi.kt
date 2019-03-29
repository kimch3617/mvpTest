package com.example.mvptest.ui.rx.like.channel

import com.example.mvptest.data.CallResponse
import com.example.mvptest.ui.rx.ActivityLifecycle
import com.example.mvptest.ui.rx.like.navigation.LikeUserViewAction
import io.reactivex.Observable

internal interface LikeUserChannelApi {
    fun ofData(): Observable<CallResponse>
    fun ofLifecycle(): Observable<ActivityLifecycle>
    fun ofViewAction(): Observable<LikeUserViewAction>
//    fun ofNavigation(): Observable<SearchUserNavigation>

    fun accept(data: CallResponse)
    fun accept(lifecycle: ActivityLifecycle)
    fun accept(viewAction: LikeUserViewAction)
//    fun accept(navigation: SearchUserNavigation)
}