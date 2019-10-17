package com.example.ants_todo.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class AntsAdapter<VH : AntsViewHolder<T>, T : Any> : RecyclerView.Adapter<VH>(), AntsBaseAdapter<T> {
    private val items: MutableList<T> = mutableListOf()
    private var callback: AntsBaseCallback<T>? = null

    override fun add(item: T) {
        items += item
        notifyItemInserted(items.lastIndex)
    }

    override fun addAll(items: List<T>) {
        if (items.isEmpty()) return

        val startPosition = this.items.lastIndex + 1
        for (item in items) {
            this.items += item
        }
        notifyItemRangeInserted(startPosition, items.size)
    }

    override fun addTo(position: Int, item: T) {
        items.add(position, item)
        notifyItemChanged(position)
    }

    override fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun clear() {
        val removedSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, removedSize)
    }

    override fun setCallback(callback: AntsBaseCallback<T>) {
        this.callback = callback
    }

    override fun update(newItems: List<T>) {
        if (callback != null) {
            callback!!.setLists(items, newItems)
            val diffResult = DiffUtil.calculateDiff(callback!!)
            clear()
            addAll(newItems)
            diffResult.dispatchUpdatesTo(this)
        } else {
            items.clear()
            items.addAll(newItems)
            notifyDataSetChanged()
        }
    }

    override fun getList(): List<T> = items

    override fun getItem(position: Int): T = items[position]

    override fun getItemCount(): Int = items.size

    protected fun inflate(parent: ViewGroup, @LayoutRes viewType: Int): View =
        LayoutInflater.from(parent.context).inflate(viewType, parent, false)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }
}