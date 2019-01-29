package com.example.myapplication.ui.adapter

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.example.myapplication.databinding.ItemFeedBinding
import com.example.myapplication.model.data.card.Card
import com.example.myapplication.ui.carddetail.CardDetailFragment

class FeedAdapter(private val activity: BaseActivity?) : BaseRecyclerViewAdapter<Card, FeedAdapter.FeedHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {
        val binding = DataBindingUtil.inflate<ItemFeedBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_feed, parent, false)
        return FeedHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        val item = getItem(holder.adapterPosition) ?: return
        holder.binding.card = item
        val cardView = holder.binding.imageCard
        Glide.with(cardView.context).load(item.imgUrl).apply(RequestOptions.centerCropTransform()).into(cardView)

        holder.itemView.setOnClickListener {
            val fragment = CardDetailFragment()
            val bundle = Bundle()
            bundle.putInt(CardDetailFragment.CARD_ID, item.id)
            fragment.arguments = bundle
            activity?.addFragment(R.id.container, fragment)
        }
    }

    class FeedHolder(val binding: ItemFeedBinding): RecyclerView.ViewHolder(binding.root)
}