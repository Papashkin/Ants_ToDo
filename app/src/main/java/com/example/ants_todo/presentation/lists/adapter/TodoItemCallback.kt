package com.example.ants_todo.presentation.lists.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.ants_todo.data.models.ListModel

class TodoItemCallback : DiffUtil.ItemCallback<ListModel>() {
    override fun areItemsTheSame(item1: ListModel, item2: ListModel): Boolean {
        return item1.id == item2.id
    }

    override fun areContentsTheSame(item1: ListModel, item2: ListModel): Boolean {
        return item1.name == item2.name
    }
}