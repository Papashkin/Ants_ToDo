package com.example.ants_todo.presentation.lists.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.databinding.ListsViewBinding


class ListsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: ListsViewBinding = DataBindingUtil.bind(itemView)!!

    fun getBinding() = binding
}