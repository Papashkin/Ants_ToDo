package com.example.ants_todo.presentation.lists

import androidx.lifecycle.LiveData
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.repositories.ListsRepository
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import org.kodein.di.erased.instance


class ListsViewModel : BaseViewModel()  {

    private val repo: ListsRepository by instance()
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

    fun undoDeleting() {
        preDeletedList.let {
            repo.insert(it!!)
        }
        preDeletedList = null
    }
}