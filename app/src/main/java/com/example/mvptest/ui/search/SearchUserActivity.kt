package com.example.mvptest.ui.search

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.SimpleItemAnimator
import android.view.Menu
import android.view.MenuItem
import com.example.mvptest.R
import com.example.mvptest.base.BaseActivity
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.ui.like.LikeUserActivity
import com.example.mvptest.util.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_search_user.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class SearchUserActivity : BaseActivity(), SearchUserContract.View {
    private lateinit var mDataSource: UserLocalDataSource
    private lateinit var mPresenter: SearchUserPresenter
    private lateinit var mAdapter: SearchUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
        EventBus.getDefault().register(this)

        mDataSource = UserLocalDataSource(this)
        mAdapter = SearchUserAdapter { position, user ->
            user?.let { it ->
                mPresenter.addLikeUser(position, it)
            }
        }

        mPresenter = SearchUserPresenter(this, mDataSource)
        mPresenter.bindView(this)

        val manager = LinearLayoutManager(this)
        recycler_users.adapter = mAdapter
        recycler_users.layoutManager = manager
        (recycler_users.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        recycler_users.addOnScrollListener(object: PaginationScrollListener(manager) {
            override fun loadMoreItems() {
                mPresenter.loadUsers()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_navigation, menu)
        val searchViewItem = menu.findItem(R.id.menu_action_search)

        val searchViewActionBar = searchViewItem.actionView as SearchView
        searchViewActionBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                mPresenter.searchQuery(q)
                searchViewActionBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(q: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_action_like -> {
                startActivity(Intent(this, LikeUserActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        mDataSource.finish()
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    @Subscribe
    fun onUserEvent(user: User) {
        val index = mAdapter.getItemList().indexOf(user)
        if (index != -1) {
            val item = mAdapter.getItem(index)
            item?.isLike = false
            mAdapter.notifyItemChanged(index)
        }
    }

    override fun addUsers(users: List<User>) {
        mAdapter.addItems(users)
    }

    override fun removeAdapterItems() {
        mAdapter.removeAll()
    }

    override fun notifyItemChanged(position: Int) {
        mAdapter.notifyItemChanged(position)
    }
}
