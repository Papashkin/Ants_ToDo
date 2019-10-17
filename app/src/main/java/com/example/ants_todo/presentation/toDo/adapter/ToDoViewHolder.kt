package com.example.ants_todo.presentation.toDo.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.databinding.TodoListViewBinding


class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: TodoListViewBinding = DataBindingUtil.bind(view)!!

    fun getBinding(): TodoListViewBinding = binding
}