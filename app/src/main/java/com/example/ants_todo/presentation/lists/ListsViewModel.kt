package com.example.ants_todo.presentation.lists

import androidx.lifecycle.MutableLiveData
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.repositories.ListsRepository
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance


class ListsViewModel : BaseViewModel() {

    private val repo: ListsRepository by instance()
    private var preDeletedList: ListModel? = null
    private var dataFromDB: List<ListModel> = listOf()

    var listModel: MutableLiveData<List<ListModel>> = MutableLiveData()

    init {
        getDataFromDB()
    }

    private fun getDataFromDB() = GlobalScope.launch {
        dataFromDB = repo.getAllAsync()
        listModel.postValue(dataFromDB)
    }

    fun addItem(item: ListModel) = GlobalScope.launch {
        repo.insertAsync(item)
        getDataFromDB()
    }

    fun deleteItem(id: Int) = GlobalScope.launch {
        preDeletedList = repo.getListByIdAsync(id)
        repo.deleteAsync(preDeletedList!!)
        getDataFromDB()
    }

    fun undoDeleting() {
        undo(preDeletedList!!)
        preDeletedList = null
    }

    private fun undo(item: ListModel) = GlobalScope.launch {
        repo.insertAsync(item)
        getDataFromDB()
    }
}