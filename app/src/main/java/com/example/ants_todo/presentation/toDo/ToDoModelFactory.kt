package com.example.ants_todo.presentation.toDo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ToDoModelFactory(private val listId: Int, private val listName: String) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ToDoViewModel(listId, listName) as T
}