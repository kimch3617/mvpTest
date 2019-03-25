package com.example.mvptest.ui.orgin.search

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.SimpleItemAnimator
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.mvptest.R
import com.example.mvptest.base.BaseActivity
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.ui.like._LikeUserActivity
import com.example.mvptest.util.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_search_user.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class _SearchUserActivity : BaseActivity(), SearchUserContract.View {

    @Inject protected lateinit var dataSource: UserLocalDataSource
    @Inject protected lateinit var presenter: SearchUserPresenter
    @Inject protected lateinit var adapter: SearchUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
        EventBus.getDefault().register(this)

        adapter.callback = { position, user ->
            Log.e("click", "$position, $user")
            user?.let {
                presenter.addLikeUser(position, it)
            }
        }

        presenter.bindView(this)
        dataSource.start()

        val manager = LinearLayoutManager(this)
        recycler_users.adapter = adapter
        recycler_users.layoutManager = manager
        (recycler_users.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        recycler_users.addOnScrollListener(object: PaginationScrollListener(manager) {
            override fun loadMoreItems() {
                presenter.loadUsers()
            }
        })
    }

    override fun onDestroy() {
        dataSource.finish()
        Log.e("_SearchUserActivity", "$dataSource")

        EventBus.getDefault().unregister(this)

        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_navigation, menu)
        val searchViewItem = menu.findItem(R.id.menu_action_search)

        val searchViewActionBar = searchViewItem.actionView as SearchView
        searchViewActionBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                presenter.searchQuery(q)
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
                startActivity(Intent(this, _LikeUserActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Subscribe
    fun onUserEvent(user: User) {
        val index = adapter.getItemList().indexOf(user)
        if (index != -1) {
            val item = adapter.getItem(index)
            item?.isLike = false
            adapter.notifyItemChanged(index)
        }
    }

    override fun addUsers(users: List<User>) {
        adapter.addItems(users)
    }

    override fun removeAdapterItems() {
        adapter.removeAll()
    }

    override fun notifyItemChanged(position: Int) {
        adapter.notifyItemChanged(position)
    }

}
