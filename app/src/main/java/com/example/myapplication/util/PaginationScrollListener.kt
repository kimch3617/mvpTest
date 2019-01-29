package com.example.myapplication.util

import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class PaginationScrollListener(val layoutManager: RecyclerView.LayoutManager): RecyclerView.OnScrollListener() {
    private var mIsDelay = false
    private var mCurrentTotalItemCount = 0

    companion object {
        private const val PRELOAD_OFFSET = 5
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dx <= 0 && dy <= 0) return
        val visibleItemCount = layoutManager.childCount
        val firstVisibleItemPosition = when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findFirstVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findFirstVisibleItemPosition()
            else -> 0
        }

        val totalItemCount = layoutManager.itemCount
        checkLoadItem(totalItemCount, visibleItemCount, firstVisibleItemPosition)
    }

    private fun checkLoadItem(totalItemCount: Int, visibleItemCount: Int, firstVisibleItemPosition: Int) {
        if (mIsDelay) return
        if (!isLoading()) {
            val total = if (totalItemCount - PRELOAD_OFFSET != 0) totalItemCount - PRELOAD_OFFSET else 0
            if (visibleItemCount + firstVisibleItemPosition >= total && firstVisibleItemPosition >= 0) {
                mIsDelay = true
                loadMoreItems()
                Handler().postDelayed({
                    mIsDelay = false
                }, 500)
            }
        }
        mCurrentTotalItemCount = totalItemCount
    }

    abstract fun loadMoreItems()

    abstract fun isLoading(): Boolean
}