package com.example.mvptest.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity: AppCompatActivity(), BaseView {
    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
