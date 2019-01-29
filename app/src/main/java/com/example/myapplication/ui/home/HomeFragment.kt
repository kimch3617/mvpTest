package com.example.myapplication.ui.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.data.card.Card
import com.example.myapplication.model.data.card.User
import com.example.myapplication.ui.adapter.CardAdapter
import com.example.myapplication.ui.adapter.UserAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeView {
    private lateinit var cardAdapter: CardAdapter
    private lateinit var userAdapter: UserAdapter

    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter()
        presenter.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardAdapter = CardAdapter(getBaseActivity())
        val recyclerCards = layout_cards.findViewById<RecyclerView>(R.id.recycler_cards)
        recyclerCards.adapter = cardAdapter
        recyclerCards.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        userAdapter = UserAdapter()
        val recyclerUsers = layout_users.findViewById<RecyclerView>(R.id.recycler_users)
        recyclerUsers.adapter = userAdapter
        recyclerUsers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        presenter.getHome()
    }

    override fun setPopularCards(popularCards: ArrayList<Card>) {
        cardAdapter.addItems(popularCards)
    }

    override fun setPopularUsers(users: ArrayList<User>) {
        userAdapter.addItems(users)
    }
}