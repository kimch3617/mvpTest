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
import com.example.myapplication.databinding.ItemPopularCardBinding
import com.example.myapplication.model.data.card.Card
import com.example.myapplication.ui.carddetail.CardDetailFragment

class CardAdapter(private val activity: BaseActivity?) : BaseRecyclerViewAdapter<Card, CardAdapter.PopularCardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCardHolder {
        val binding = DataBindingUtil.inflate<ItemPopularCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_popular_card, parent, false)
        return PopularCardHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularCardHolder, position: Int) {
        val item = getItem(holder.adapterPosition) ?: return
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

    class PopularCardHolder(val binding: ItemPopularCardBinding): RecyclerView.ViewHolder(binding.root)

}