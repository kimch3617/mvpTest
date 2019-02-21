package com.example.mvptest.ui.like

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvptest.R
import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.data.User
import com.example.mvptest.databinding.ItemUserBinding

class LikeUserAdapter(val clickCallback: (user: User?) -> Unit): BaseRecyclerViewAdapter<User, LikeUserAdapter.UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val holder = UserHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_user, parent, false
            )
        )
        holder.itemView.setOnClickListener {
            clickCallback(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val item = getItem(holder.adapterPosition) ?: return
        holder.binding.user = item
        holder.binding.viewLike.isSelected = item.isLike
        val userImage = holder.binding.userImage
        Glide.with(userImage.context).load(item.avatarUrl).apply(RequestOptions.centerInsideTransform()).into(userImage)
    }

    class UserHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)
}