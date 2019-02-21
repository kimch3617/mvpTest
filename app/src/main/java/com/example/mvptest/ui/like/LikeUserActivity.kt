package com.example.mvptest.ui.like

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SimpleItemAnimator
import com.example.mvptest.R
import com.example.mvptest.base.BaseActivity
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import kotlinx.android.synthetic.main.activity_like_user.*
import org.greenrobot.eventbus.EventBus

class LikeUserActivity : BaseActivity(), LikeUserContract.View {
    private lateinit var mDataSource: UserLocalDataSource
    private lateinit var mPresenter: LikeUserPresenter
    private lateinit var mAdapter: LikeUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_user)

        mDataSource = UserLocalDataSource(this)
        mAdapter = LikeUserAdapter { user ->
            user?.let {
                mPresenter.removeLikeUser(it)
            }
        }

        mPresenter = LikeUserPresenter(mDataSource)
        mPresenter.bindView(this)

        val manager = LinearLayoutManager(this)
        recycler_users.adapter = mAdapter
        recycler_users.layoutManager = manager
        (recycler_users.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

        mPresenter.loadLikeUser()
    }

    override fun onDestroy() {
        mDataSource.finish()
        super.onDestroy()
    }

    override fun addUsers(users: List<User>) {
        mAdapter.addItems(users)
    }

    override fun removeUser(user: User) {
        mAdapter.removeItem(user)
    }

    override fun postEventUser(user: User) {
        EventBus.getDefault().post(user)
    }
}
