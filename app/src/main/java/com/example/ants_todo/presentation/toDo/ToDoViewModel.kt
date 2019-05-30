package com.example.ants_todo.presentation.toDo

import androidx.lifecycle.MutableLiveData
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.data.repositories.ToDoRepository
import com.example.ants_todo.presentation.ToDoApplication
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.erased.instance

class ToDoViewModel(private val listId: Int, private val listName: String) : BaseViewModel() {

    override val kodein: Kodein = ToDoApplication.getKodein()

    private val toDoRepo: ToDoRepository by instance()
    private var preDeletedToDo: ToDoModel? = null
    private var dataFromDB: List<ToDoModel> = listOf()

    var toDos: MutableLiveData<List<ToDoModel>> = MutableLiveData()

    init {
        getDataFromDB()
    }

    private fun getDataFromDB() = GlobalScope.launch {
        dataFromDB = toDoRepo.getToDos(listId)
        toDos.postValue(dataFromDB)
    }

    fun addItem(item: ToDoModel) = GlobalScope.launch {
        toDoRepo.insert(item)
        getDataFromDB()
    }

    fun deleteItem(id: Int) = GlobalScope.launch {
        preDeletedToDo = toDoRepo.getById(id)
        toDoRepo.delete(preDeletedToDo!!)
        getDataFromDB()
    }

    fun updateItem(id: Int) = GlobalScope.launch {
        val updatedItem = toDoRepo.getById(id)
        updatedItem.isChecked = !updatedItem.isChecked
        toDoRepo.update(updatedItem)
        getDataFromDB()
    }

    fun undoDeleting() = GlobalScope.launch {
        toDoRepo.insert(preDeletedToDo!!)
        getDataFromDB()
        preDeletedToDo = null
    }

    fun getListName(): String = this.listName
}