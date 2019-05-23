package com.example.ants_todo.presentation.toDo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein

class ToDoModelFactory(private val listId: Int, private val kodein: Kodein) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ToDoViewModel(listId, kodein) as T
}