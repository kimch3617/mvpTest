package com.example.mvptest.ui.rx.search.channel

import com.example.mvptest.data.CallResponse
import com.example.mvptest.data.RemoteData
import com.example.mvptest.ui.rx.ActivityLifecycle
import com.example.mvptest.ui.rx.search.dto.SearchUserViewAction
import com.example.mvptest.ui.rx.search.navigation.SearchUserNavigation
import io.reactivex.Observable

internal interface SearchUserChannelApi {
    fun ofData(): Observable<CallResponse>
    fun ofLifecycle(): Observable<ActivityLifecycle>
    fun ofViewAction(): Observable<SearchUserViewAction>
    fun ofNavigation(): Observable<SearchUserNavigation>

    fun accept(data: CallResponse)
    fun accept(lifecycle: ActivityLifecycle)
    fun accept(viewAction: SearchUserViewAction)
    fun accept(navigation: SearchUserNavigation)
}