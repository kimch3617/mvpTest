package com.example.mvptest.base

import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> {

    private var view: WeakReference<V>? = null

    fun bindView(view: V) {
        this.view = WeakReference(view)
    }

    fun unbindView() {
        this.view = null
    }

    protected fun getView(): V? {
        return if (view == null) {
            null
        } else {
            view!!.get()
        }
    }

}