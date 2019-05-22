package com.example.ants_todo.presentation.toDo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ants_todo.ToDoApplication
import com.example.ants_todo.data.db.toDo.ToDoDao
import com.example.ants_todo.data.models.ToDoModel
import org.kodein.di.erased.instance

class ToDoViewModel constructor(app: Application) : AndroidViewModel(app) {

    private val dao: ToDoDao by (app as ToDoApplication).kodein.instance()

    private var toDos: LiveData<List<ToDoModel>>
    init {
        toDos = dao.getToDos(0)
    }

    fun setList(id: Int) {
        toDos = dao.getToDos(id)
    }

    fun addItem(item: ToDoModel) {
        dao.add(item)
    }

    fun deleteItem(id: Int) {
        val selectedItem = dao.getById(id)
        dao.delete(selectedItem)
    }

    fun updateItem(id: Int) {
        val updatedItem = dao.getById(id)
        updatedItem.isChecked != updatedItem.isChecked
        dao.update(updatedItem)
    }
}