package com.example.homework

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homework.base.BaseRecyclerViewAdapter
import com.example.homework.databinding.ItemReposBinding
import com.example.homework.databinding.ItemUserBinding
import com.example.homework.model.Repos
import com.example.homework.model.RestData
import com.example.homework.model.User

class ReposAdapter : BaseRecyclerViewAdapter<RestData, RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_NONE = 0
        private const val TYPE_USER = 1
        private const val TYPE_REPOS = 2

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_USER -> {
                val binding = DataBindingUtil.inflate<ItemUserBinding>(
                    LayoutInflater.from(parent.context), R.layout.item_user, parent, false)
                UserHolder(binding)
            }
            TYPE_REPOS -> {
                val binding = DataBindingUtil.inflate<ItemReposBinding>(
                    LayoutInflater.from(parent.context), R.layout.item_repos, parent, false)
                ReposHolder(binding)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition) ?: return
        when (holder) {
            is UserHolder -> {
                val user = item as User
                val imageView = holder.binding.avatarImage
                Glide.with(imageView.context).load(user.avatarUrl).apply(RequestOptions.centerCropTransform()).into(imageView)
                holder.binding.username.text = user.login

            }
            is ReposHolder -> {
                holder.binding.repos = item as Repos
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is User -> TYPE_USER
            is Repos -> TYPE_REPOS
            else -> TYPE_NONE
        }
    }

    class UserHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)
    class ReposHolder(val binding: ItemReposBinding): RecyclerView.ViewHolder(binding.root)

}