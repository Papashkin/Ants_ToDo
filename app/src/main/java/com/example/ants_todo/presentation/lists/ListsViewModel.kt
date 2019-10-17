package com.example.ants_todo.presentation.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.db.lists.ListsRepository
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance


class ListsViewModel : BaseViewModel() {

    private val listsRepository: ListsRepository by instance()
    private var preDeletedList: ListModel? = null

    val isExisted: MutableLiveData<Boolean>
    val listModel: LiveData<List<ListModel>>
    init {
        listModel = liveData { emitSource(listsRepository.getAllAsync().await()) }
        isExisted = MutableLiveData(false)
    }

    fun addItem(name: String) = viewModelScope.launch {
        val existedOne = listsRepository.getByNameAsync(name).await()
        isExisted.postValue(existedOne != null)
        if (existedOne == null) {
            val item = ListModel(name = name)
            listsRepository.insertAsync(item).await()
        }
    }

    fun deleteItem(id: Int) = viewModelScope.launch {
        preDeletedList = listsRepository.getListByIdAsync(id).await()
        listsRepository.deleteAsync(preDeletedList!!).await()
    }

    fun undoDeleting() {
        undo(preDeletedList!!)
        preDeletedList = null
    }

    private fun undo(item: ListModel) = viewModelScope.launch {
        listsRepository.insertAsync(item).await()
    }
}