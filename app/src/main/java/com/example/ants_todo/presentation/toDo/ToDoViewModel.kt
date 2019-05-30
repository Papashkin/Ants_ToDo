package com.example.ants_todo.presentation.toDo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.data.repositories.ToDoRepository
import com.example.ants_todo.presentation.ToDoApplication
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.erased.instance


class ToDoViewModel(private val listId: Int, private val listName: String) : BaseViewModel() {

    override val kodein: Kodein = ToDoApplication.getKodein()

    private val toDoRepo: ToDoRepository by instance()
    private var preDeletedToDo: ToDoModel? = null

    val toDos: LiveData<List<ToDoModel>>
    init {
        toDos = liveData {
            emitSource(toDoRepo.getToDosAsync(listId).await())
        }
    }

    fun addItem(item: ToDoModel) = viewModelScope.launch {
        toDoRepo.insert(item).await()
    }

    fun deleteItem(id: Int) = viewModelScope.launch {
        preDeletedToDo = toDoRepo.getById(id).await()
        toDoRepo.delete(preDeletedToDo!!).await()
    }

    fun updateItem(id: Int) = viewModelScope.launch {
        val updatedItem = toDoRepo.getById(id).await()
        updatedItem.isChecked = !updatedItem.isChecked
        toDoRepo.update(updatedItem).await()
    }

    fun undoDeleting() = viewModelScope.launch {
        toDoRepo.insert(preDeletedToDo!!).await()
        preDeletedToDo = null
    }

    fun getListName(): String = this.listName
}