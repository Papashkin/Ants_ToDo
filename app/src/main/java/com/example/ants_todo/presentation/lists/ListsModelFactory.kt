package com.example.ants_todo.presentation.lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein

class ListsModelFactory(private val kodein: Kodein) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ListsViewModel(kodein) as T
}