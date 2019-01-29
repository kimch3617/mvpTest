package com.example.myapplication.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.example.myapplication.databinding.ItemPopularUserBinding
import com.example.myapplication.model.data.card.User

class UserAdapter : BaseRecyclerViewAdapter<User, UserAdapter.PopularUserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularUserHolder {
        val binding = DataBindingUtil.inflate<ItemPopularUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_popular_user, parent, false)
        return PopularUserHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularUserHolder, position: Int) {
        val item = getItem(holder.adapterPosition) ?: return
        holder.binding.user = item
    }

    class PopularUserHolder(val binding: ItemPopularUserBinding): RecyclerView.ViewHolder(binding.root)

}