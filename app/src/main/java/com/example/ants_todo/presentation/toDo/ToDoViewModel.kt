package com.example.ants_todo.presentation.toDo

import androidx.lifecycle.MutableLiveData
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.data.repositories.ToDoRepository
import com.example.ants_todo.presentation.ToDoApplication
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.erased.instance


class ToDoViewModel(private val listId: Int, private val listName: String) : BaseViewModel() {

    override val kodein: Kodein = ToDoApplication.getKodein()

    private val toDoRepository: ToDoRepository by instance()
    private var preDeletedToDo: ToDoModel? = null

    var toDos: MutableLiveData<List<ToDoModel>> = MutableLiveData()

    init {
        getDataFromDB()
    }

    private fun getDataFromDB() = CoroutineScope(Dispatchers.Default).launch {
        toDos.postValue(toDoRepository.getToDos(listId))
    }

    fun addItem(item: ToDoModel) = CoroutineScope(Dispatchers.Default).launch {
        toDoRepository.insert(item)
        getDataFromDB()
    }

    fun deleteItem(id: Int) = CoroutineScope(Dispatchers.Default).launch {
        preDeletedToDo = toDoRepository.getById(id)
        toDoRepository.delete(preDeletedToDo!!)
        getDataFromDB()
    }

    fun updateItem(id: Int) = CoroutineScope(Dispatchers.Default).launch {
        val updatedItem = toDoRepository.getById(id)
        updatedItem.isChecked = !updatedItem.isChecked
        toDoRepository.update(updatedItem)
        getDataFromDB()
    }

    fun undoDeleting() = CoroutineScope(Dispatchers.Default).launch {
        toDoRepository.insert(preDeletedToDo!!)
        getDataFromDB()
        preDeletedToDo = null
    }

    fun getListName(): String = this.listName
}