package com.example.mvptest.ui.orgin.search

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvptest.R
import com.example.mvptest.base.BaseRecyclerViewAdapter
import com.example.mvptest.data.User
import kotlinx.android.synthetic.main.item_user_no_binding.view.*
import javax.inject.Inject

class SearchUserAdapter @Inject constructor(): BaseRecyclerViewAdapter<User, SearchUserAdapter.UserHolder>() {

    lateinit var callback: (position: Int, user: User?) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val holder = UserHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_no_binding,
                null
            )
        )
        holder.itemView.setOnClickListener {
            val item = getItem(holder.adapterPosition)
            callback(holder.adapterPosition, item)
        }

        return holder
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val item = getItem(holder.adapterPosition) ?: return
        holder.bind(item)
//        holder.binding.user = item
//        holder.binding.viewLike.isSelected = item.isLike
//        val userImage = holder.binding.userImage
//        Glide.with(userImage.context).load(item.avatarUrl).apply(RequestOptions.centerInsideTransform()).into(userImage)
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: User) {
            with(itemView) {
                user_name.text = item.login
                user_url.text = item.url
                view_like.isSelected = item.isLike
                Glide.with(user_image.context).load(item.avatarUrl).apply(RequestOptions.centerInsideTransform()).into(user_image)
            }
        }
    }
//    class UserHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)

}