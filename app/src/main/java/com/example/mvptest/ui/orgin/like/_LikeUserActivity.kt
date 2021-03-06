package com.example.mvptest.ui.like

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.mvptest.R
import com.example.mvptest.base._BaseActivity
import com.example.mvptest.data.User
import com.example.mvptest.repository.local.UserLocalDataSource
import kotlinx.android.synthetic.main.activity_like_user.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class _LikeUserActivity : _BaseActivity(), LikeUserContract.View {
    
    @Inject protected lateinit var dataSource: UserLocalDataSource
    @Inject protected lateinit var presenter: LikeUserPresenter
    @Inject protected lateinit var adapter: _LikeUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_user)

        adapter.clickCallback = { _, user ->
            user?.let {
                presenter.removeLikeUser(it)
            }
        }

        presenter.bindView(this)
        dataSource.start()

        val manager = LinearLayoutManager(this)
        recycler_users.adapter = adapter
        recycler_users.layoutManager = manager
        (recycler_users.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

        presenter.loadLikeUser()
    }

    override fun onDestroy() {
        dataSource.finish()

        super.onDestroy()
    }

    override fun addUsers(users: List<User>) {
        adapter.addItems(users)
    }

    override fun removeUser(user: User) {
        adapter.removeItem(user)
    }

    override fun postEventUser(user: User) {
        EventBus.getDefault().post(user)
    }
    
}
