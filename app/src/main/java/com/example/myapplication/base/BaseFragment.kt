package com.example.myapplication.base

import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment(), BaseView {
    override fun showToast(msg: String) {
        getBaseActivity()?.showToast(msg)
    }

    fun getBaseActivity(): BaseActivity? {
        return activity as? BaseActivity
    }
}