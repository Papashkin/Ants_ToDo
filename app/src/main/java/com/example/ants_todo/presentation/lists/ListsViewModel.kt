package com.example.ants_todo.presentation.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.repositories.ListsRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.instance


class ListsViewModel(kodein: Kodein) : ViewModel()  {

    private val repo: ListsRepository by kodein.instance()
    private var preDeletedList: ListModel? = null

    val listModel: LiveData<List<ListModel>>
    init {
        listModel = repo.getAll()
    }

    fun addItem(item: ListModel) = repo.insert(item)

    fun deleteItem(id: Int) {
        preDeletedList = repo.getListById(id)
        repo.delete(preDeletedList!!)
    }

    fun undoDelete() {
        preDeletedList.let {
            repo.insert(it!!)
        }
        preDeletedList = null
    }
}