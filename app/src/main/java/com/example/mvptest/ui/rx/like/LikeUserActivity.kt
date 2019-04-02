package com.example.mvptest.ui.like

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.mvptest.R
import com.example.mvptest.base.BaseActivity
import com.example.mvptest.repository.local.UserLocalDataSource
import com.example.mvptest.ui.rx.ActivityLifecycle
import com.example.mvptest.ui.rx.like.dto.LikeUserLooknFeel
import com.example.mvptest.ui.rx.like.navigation.LikeUserViewAction
import com.example.mvptest.ui.rx.like.viewModel.LikeUserViewModel
import kotlinx.android.synthetic.main.activity_like_user.*
import javax.inject.Inject

class LikeUserActivity : BaseActivity() {
    
    @Inject protected lateinit var dataSource: UserLocalDataSource
    @Inject protected lateinit var adapter: LikeUserAdapter

    private val viewModel by lazy { createViewModel(LikeUserViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_user)

        viewModel.channel.accept(ActivityLifecycle.OnCreate(intent, savedInstanceState))

        initAdapter()

        observeLiveData()
    }

    override fun onResume() {
        super.onResume()

        Log.e("Like", "OnResume")
//        viewModel.channel.accept(ActivityLifecycle.OnResume())
    }

    override fun onDestroy() {
        dataSource.finish()

        super.onDestroy()
    }

    private fun initAdapter() {
        adapter.clickCallback = { position, user ->
            user?.let {
                viewModel.channel.accept(LikeUserViewAction.OnLikeDeleted(position, user))
            }
        }

        val manager = LinearLayoutManager(this)
        recycler_users.adapter = adapter
        recycler_users.layoutManager = manager
        (recycler_users.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
    }

    private fun observeLiveData() {
        viewModel.loadLikeUserResponse.observe(this, Observer {
            loadUsersInRecyclerView(it)
        })
        viewModel.deleteLikeResponse.observe(this, Observer {
            deleteUserInRecyclerView(it)
        })
    }

    private fun loadUsersInRecyclerView(looknFeel: LikeUserLooknFeel.LikeUsers) {
        looknFeel.users?.let { adapter.addItems(it) }
    }

    private fun deleteUserInRecyclerView(looknFeel: LikeUserLooknFeel.DeleteLikeUser) {
        adapter.removeItem(looknFeel.user)
    }

//    override fun addUsers(users: List<User>) {
//        adapter.addItems(users)
//    }
//
//    override fun removeUser(user: User) {
//        adapter.removeItem(user)
//    }
//
//    override fun postEventUser(user: User) {
//        EventBus.getDefault().post(user)
//    }
    
}
