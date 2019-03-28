package com.example.mvptest.ui.rx.search

import Flavors
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.mvptest.R
import com.example.mvptest.base.BaseActivity
import com.example.mvptest.data.CallResponse
import com.example.mvptest.data.SearchUsers
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.ui.like._LikeUserActivity
import com.example.mvptest.ui.rx.search.dto.LikeUserResult
import com.example.mvptest.ui.rx.search.dto.SearchUserViewAction
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

        observeLiveData()

        Log.e("viewModel", "${viewModel.clickSearch}")

//        dataSource.start()
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

//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        searchViewActionBar.apply {
//            Log.e("componentName", "componentName:$componentName")
//            setSearchableInfo(searchManager.getSearchableInfo(ComponentName(applicationContext, SearchUserActivity::class.java)))
//        }

        searchViewActionBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                Log.e("onQueryTextSubmit", "query: $q")

                adapter.removeAll()

                viewModel.channel.accept(SearchUserViewAction.OnSearchClicked(q ?: ""))
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
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initAdapter() {
        adapter.callback = { position, user ->
            Log.e("click", "$position, $user")
            user?.let {
                if(!it.isLike) {
                    viewModel.channel.accept(SearchUserViewAction.OnLikeClicked(position, user))
                }
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

    private fun observeLiveData() {
        viewModel.searchDataResponse.observe(this, Observer {
            setSearchUser(it)
        })
        viewModel.likeData.observe(this, Observer {
            setLikeUser(it)
        })
    }

    private fun setSearchUser(response: CallResponse) {
        Log.e("setSearchDataForAdapter", "$response")
        when(response) {
            is CallResponse.Loading -> {
                Log.e("Loading", "${response.isLoading}")
            }

            is CallResponse.Success<*> -> {
                if(response.data is SearchUsers) {
                    adapter.addItems(response.data.items)
                }
            }

            is CallResponse.Error -> {
                Toast.makeText(this, "${response.err}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setLikeUser(response: CallResponse) {
        Log.e("setLikeUser", "$response")
        when(response) {
            is CallResponse.Loading -> {
                Log.e("Loading", "${response.isLoading}")
            }

            is CallResponse.Success<*> -> {
                if(response.data is LikeUserResult) {
                    val position = response.data.position
                    adapter.getItem(position)?.isLike = response.data.result
                    adapter.notifyItemChanged(position)
                }
            }

            is CallResponse.Error -> {
                Toast.makeText(this, "${response.err}", Toast.LENGTH_SHORT).show()
            }
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

}
