package com.example.ants_todo.presentation.lists

import androidx.lifecycle.MutableLiveData
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.repositories.ListsRepository
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance


class ListsViewModel : BaseViewModel() {

    private val listsRepository: ListsRepository by instance()
    private var preDeletedList: ListModel? = null

    var listModel: MutableLiveData<List<ListModel>> = MutableLiveData()

    init {
        getDataFromDB()
    }

    private fun getDataFromDB() = CoroutineScope(Dispatchers.Default).launch {
        listModel.postValue(listsRepository.getAllAsync())
    }

    fun addItem(item: ListModel) = CoroutineScope(Dispatchers.Default).launch {
        listsRepository.insertAsync(item)
        getDataFromDB()
    }

    fun deleteItem(id: Int) = CoroutineScope(Dispatchers.Default).launch {
        preDeletedList = listsRepository.getListByIdAsync(id)
        listsRepository.deleteAsync(preDeletedList!!)
        getDataFromDB()
    }

    fun undoDeleting() {
        undo(preDeletedList!!)
        preDeletedList = null
    }

    private fun undo(item: ListModel) = CoroutineScope(Dispatchers.Default).launch {
        listsRepository.insertAsync(item)
        getDataFromDB()
    }
}