package com.example.homework.base

import android.support.v7.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    private val itemList: ArrayList<T> = arrayListOf()

    fun getItemList(): ArrayList<T> = itemList

    fun addItems(list: List<T>, index: Int = itemCount) {
        if (list.isEmpty()) return
        val addIndex = if (index > itemCount) itemCount else index
        itemList.addAll(addIndex, list)
        notifyItemRangeInserted(addIndex, list.size)
    }

    fun addItem(item: T, index: Int = itemCount) {
        val addIndex = if (index > itemCount) itemCount else index
        itemList.add(addIndex, item)
        notifyItemInserted(addIndex)
    }

    fun removeItem(item: T?): Int {
        val position = itemList.indexOf(item)
        if (position != -1) {
            itemList.removeAt(position)
            notifyItemRemoved(position)
        }
        return position
    }

    fun removeItems(item: T?, size: Int) {
        val position = itemList.indexOf(item)
        if (position != -1) {
            val endPosition = if (position + size > itemCount) itemCount else position + size
            for (index in position until endPosition) {
                itemList.removeAt(position)
            }
            notifyItemRangeRemoved(position, size)
        }
    }

    fun removeAll() {
        notifyItemRangeRemoved(0, itemCount)
        itemList.clear()
    }

    fun getItem(position: Int): T? {
        return if (position == -1) null else itemList[position]
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}