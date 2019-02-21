package com.example.homework

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.homework.base.BaseActivity
import com.example.homework.model.Repos
import com.example.homework.model.User
import kotlinx.android.synthetic.main.activity_repos.*
import java.util.*

class ReposActivity : BaseActivity(), ReposView{
    private lateinit var reposAdapter: ReposAdapter
    private lateinit var presenter: ReposPresenter
    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        username = intent.data?.lastPathSegment ?: ""
        Log.e("username", username)

        reposAdapter = ReposAdapter()
        recycler_repos.adapter = reposAdapter
        recycler_repos.layoutManager = LinearLayoutManager(this)

        presenter = ReposPresenter()
        presenter.bindView(this)
        presenter.getUsername(username)
    }

    override fun setUserProfile(user: User) {
        presenter.getRepos(username)
        reposAdapter.addItem(user)
    }

    override fun setUserRepos(reposList: ArrayList<Repos>) {
        reposList.sortWith(Comparator { lhs, rhs ->
            when {
                lhs.stargazersCount > rhs.stargazersCount -> -1
                lhs.stargazersCount < rhs.stargazersCount -> 1
                else -> 0
            }
        })

        reposAdapter.addItems(reposList)
    }
}
