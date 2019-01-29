package com.example.myapplication.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

class MainPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {
    private val mFragments = ArrayList<Fragment>()
    private val mFragmentsTitles = ArrayList<String>()

    fun addFragment(fragment: Fragment, title: String) {
        mFragments.add(fragment)
        mFragmentsTitles.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentsTitles[position]
    }
}