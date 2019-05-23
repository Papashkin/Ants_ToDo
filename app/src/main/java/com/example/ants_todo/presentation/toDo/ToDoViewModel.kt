package com.example.ants_todo.presentation.toDo

import androidx.lifecycle.LiveData
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.data.repositories.ToDoRepository
import com.example.ants_todo.presentation.ToDoApplication
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import org.kodein.di.Kodein
import org.kodein.di.erased.instance

class ToDoViewModel(listId: Int) : BaseViewModel() {

    override val kodein: Kodein = ToDoApplication.getKodein()

    private val toDoRepo: ToDoRepository by instance()
    private var preDeletedToDo: ToDoModel? = null

    var toDos: LiveData<List<ToDoModel>>
    init {
        toDos = toDoRepo.getToDos(listId)
    }

    fun addItem(item: ToDoModel) {
        toDoRepo.insert(item)
    }

    fun deleteItem(id: Int) {
        preDeletedToDo = toDoRepo.getById(id)
        toDoRepo.delete(preDeletedToDo!!)
    }

    fun updateItem(id: Int) {
        val updatedItem = toDoRepo.getById(id)
        updatedItem.isChecked = !updatedItem.isChecked
        toDoRepo.update(updatedItem)
    }

    fun undoDeleting() {
        if (preDeletedToDo != null) {
            toDoRepo.insert(preDeletedToDo!!)
            preDeletedToDo = null
        }
    }
}