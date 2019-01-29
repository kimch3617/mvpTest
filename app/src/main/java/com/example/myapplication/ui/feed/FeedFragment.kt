package com.example.myapplication.ui.feed

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentFeedsBinding
import com.example.myapplication.model.data.card.Card
import com.example.myapplication.ui.adapter.FeedAdapter
import com.example.myapplication.util.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_feeds.*

class FeedFragment : BaseFragment(), FeedView {
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var presenter: FeedPresenter

    private var offset = 1
    private var loadingPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FeedPresenter()
        presenter.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentFeedsBinding>(inflater, R.layout.fragment_feeds, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedAdapter = FeedAdapter(getBaseActivity())
        val manager = LinearLayoutManager(context)
        recycler_feeds.adapter = feedAdapter
        recycler_feeds.layoutManager = manager
        recycler_feeds.addOnScrollListener(object: PaginationScrollListener(manager) {
            override fun loadMoreItems() {
                presenter.getCards(offset)
            }

            override fun isLoading(): Boolean {
                return loadingPage
            }
        })
        presenter.getCards(offset)
    }

    override fun loading() {
        loadingPage = true
    }

    override fun loaded() {
        loadingPage = false
    }

    override fun setCards(cards: ArrayList<Card>) {
        if (!cards.isEmpty()) offset++
        feedAdapter.addItems(cards)
    }
}