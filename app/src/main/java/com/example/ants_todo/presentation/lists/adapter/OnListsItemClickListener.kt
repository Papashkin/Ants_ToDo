package com.example.ants_todo.presentation.lists.adapter

import com.example.ants_todo.data.models.ListModel


interface OnListsItemClickListener {
    fun onClick(item: ListModel)
}