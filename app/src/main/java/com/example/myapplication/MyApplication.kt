package com.example.myapplication

import android.app.Application

class MyApplication : Application() {
    companion object {
        var isLogin = false
    }
}