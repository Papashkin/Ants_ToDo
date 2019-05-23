package com.example.ants_todo.presentation.toDo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.data.repositories.ToDoRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.instance

class ToDoViewModel(listId: Int, kodein: Kodein) : ViewModel() {

    private val toDoRepo: ToDoRepository by kodein.instance()
    private var preDeletedToDo: ToDoModel? = null

    var toDos: LiveData<List<ToDoModel>>

    init {
        toDos = toDoRepo.getToDos(listId)
    }

    fun addItem(item: ToDoModel) {
        toDoRepo.insert(item)
    }

    fun deleteItem(id: Int) {
        val preDeletedToDo = toDoRepo.getById(id)
        toDoRepo.delete(preDeletedToDo)
    }

    fun updateItem(id: Int) {
        val updatedItem = toDoRepo.getById(id)
        updatedItem.isChecked != updatedItem.isChecked
        toDoRepo.update(updatedItem)
    }

    fun undoDleting() {
        if (preDeletedToDo != null) {
            toDoRepo.insert(preDeletedToDo!!)
            preDeletedToDo = null
        }
    }
}