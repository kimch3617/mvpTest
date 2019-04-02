package com.example.mvptest.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var factory: ViewModelProvider.Factory

    protected val activityScopeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()

        activityScopeDisposable.clear()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    protected fun <VM: ViewModel> createViewModel(clazz: Class<VM>): VM {
        return ViewModelProviders.of(this, factory).get(clazz)
    }
}
