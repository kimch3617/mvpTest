package com.example.mvptest.ui.rx.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.mvptest.R
import com.example.mvptest.base.BaseActivity
import com.example.mvptest.base.ViewModelProviderFactory
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.ui.like._LikeUserActivity
import com.example.mvptest.ui.orgin.search._SearchUserAdapter
import com.example.mvptest.ui.rx.search.viewModel.SearchUserViewModel
import com.example.mvptest.util.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_search_user.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class SearchUserActivity : BaseActivity() {

    @Inject protected lateinit var dataSource: UserLocalDataSource
    @Inject protected lateinit var adapter: SearchUserAdapter

    private val viewModel by lazy { createViewModel(SearchUserViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
        supportActionBar?.title = Flavors.name

        EventBus.getDefault().register(this)

        initAdapter()

        initListener()

        observeLiveData()

        Log.e("viewModel", "${viewModel.clickSearch}")

//        dataSource.start()
    }

    override fun onDestroy() {
//        dataSource.finish()
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

    private fun initAdapter() {
        adapter.callback = { position, user ->
            Log.e("click", "$position, $user")
            user?.let {

            }
        }

        val manager = LinearLayoutManager(this)
        recycler_users.adapter = adapter
        recycler_users.layoutManager = manager
        (recycler_users.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        recycler_users.addOnScrollListener(object: PaginationScrollListener(manager) {
            override fun loadMoreItems() {

            }
        })
    }

    private fun initListener() {

    }

    private fun observeLiveData() {

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

}
