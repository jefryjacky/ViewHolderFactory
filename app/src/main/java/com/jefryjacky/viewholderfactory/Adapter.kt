package com.jefryjacky.viewholderfactory

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class Adapter : ListAdapter<String, ItemViewHolder>(AdapterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolderFactory.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val value = getItem(position)
        holder.bind(value)
    }

    class AdapterDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

}