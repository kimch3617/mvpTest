package com.example.mvptest.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

internal open class BaseViewModel: ViewModel() {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

}