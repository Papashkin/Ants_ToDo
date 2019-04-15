package com.example.ants_todo.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ants_todo.ToDoApplication
import com.example.ants_todo.data.ToDoList
import com.example.ants_todo.data.lists.ToDoListDao
import org.kodein.di.erased.instance

class ListsViewModel(app: Application) : AndroidViewModel(app) {

    private val dao: ToDoListDao by (app as ToDoApplication).kodein.instance()

    val lists: LiveData<List<ToDoList>>
    init {
        lists = dao.getAll()
    }

    fun addItem(item: ToDoList) = dao.add(item)

    fun deleteItem(id: Int) {
        val selectedItem = dao.getById(id)
        dao.delete(selectedItem)
    }

    fun selectItem() {

    }
}