package com.example.myapplication.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.feed.FeedFragment
import com.example.myapplication.ui.login.LoginFragment
import com.example.myapplication.ui.register.RegisterFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var loginView: View? = null
    private var btnLogin: Button? = null
    private var btnRegister: Button? = null
    private var btnLogout: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginView = layoutInflater.inflate(R.layout.toolbar_login, null)
        btnLogin = loginView?.findViewById<Button>(R.id.btn_login)
        btnRegister = loginView?.findViewById<Button>(R.id.btn_register)
        btnLogout = loginView?.findViewById<Button>(R.id.btn_logout)
        setLoginActionBar()

        val pagerTitle = arrayOf(R.string.pager1, R.string.pager2)
        val pagerFragment: Array<Fragment> = arrayOf(HomeFragment(), FeedFragment())

        val pagerAdapter = MainPagerAdapter(supportFragmentManager)
        for (i in pagerFragment.indices) {
            pagerAdapter.addFragment(pagerFragment[i], getString(pagerTitle[i]))
        }

        main_pager.adapter = pagerAdapter
        main_pager.clipChildren = false
        main_pager.clipToPadding = false
        main_tab.setupWithViewPager(main_pager)
    }

    private fun setLoginActionBar() {
        btnLogin?.setOnClickListener {
            addFragment(R.id.container, LoginFragment())
        }
        btnRegister?.setOnClickListener {
            addFragment(R.id.container, RegisterFragment())
        }
        btnLogout?.setOnClickListener {
            setLogout()
        }

        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.customView = loginView
    }

    fun setLogin() {
        MyApplication.isLogin = true
        btnLogin?.visibility = View.GONE
        btnRegister?.visibility = View.GONE
        btnLogout?.visibility = View.VISIBLE
    }

    fun setLogout() {
        MyApplication.isLogin = false
        btnLogin?.visibility = View.VISIBLE
        btnRegister?.visibility = View.VISIBLE
        btnLogout?.visibility = View.GONE
    }
}
