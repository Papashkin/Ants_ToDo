package com.example.ants_todo.presentation.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.repositories.ListsRepository
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance


class ListsViewModel : BaseViewModel() {

    private val repo: ListsRepository by instance()
    private var preDeletedList: ListModel? = null

    val listModel: LiveData<List<ListModel>>
    init {
        listModel = liveData {
            emitSource(repo.getAllAsync().await())
        }
    }

    fun addItem(item: ListModel) = viewModelScope.launch {
        repo.insertAsync(item)
    }

    fun deleteItem(id: Int) = viewModelScope.launch {
        preDeletedList = repo.getListByIdAsync(id)
        repo.deleteAsync(preDeletedList!!)
    }

    fun undoDeleting() {
        undo(preDeletedList!!)
        preDeletedList = null
    }

    private fun undo(item: ListModel) = viewModelScope.launch {
        repo.insertAsync(item)
    }
}