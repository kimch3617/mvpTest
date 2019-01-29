package com.example.myapplication.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity: AppCompatActivity(), BaseView {
    fun addFragment(containerViewId: Int, fragment: Fragment) {
        val manager = this.supportFragmentManager
        val backStackCount = manager.backStackEntryCount

        val fragmentTransaction = manager.beginTransaction()
        if (backStackCount > 0) {
            val backStack = manager.getBackStackEntryAt(backStackCount - 1)
            val currentFragment = manager.findFragmentByTag(backStack.name) as Fragment
//            if (currentFragment.javaClass.name == fragment.javaClass.name) return

            fragmentTransaction
                .hide(currentFragment)
                .add(containerViewId, fragment, fragment.javaClass.name)
        } else {
            fragmentTransaction
                .add(containerViewId, fragment, fragment.javaClass.name)
        }

        fragmentTransaction.addToBackStack(fragment.javaClass.name).commit()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
