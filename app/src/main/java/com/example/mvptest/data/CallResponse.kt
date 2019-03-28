package com.example.mvptest.data

sealed class CallResponse {
    class Loading(val isLoading: Boolean) : CallResponse()

    class Success<T : RemoteData>(val data: T?) : CallResponse()

    class Error(val err: Throwable) : CallResponse()
}