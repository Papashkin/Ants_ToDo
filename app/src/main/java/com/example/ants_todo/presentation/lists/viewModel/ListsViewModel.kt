package com.example.ants_todo.presentation.lists.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ants_todo.ToDoApplication
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.db.lists.ListsDao
import org.kodein.di.erased.instance

class ListsViewModel(app: Application) : AndroidViewModel(app) {

    private val dao: ListsDao by (app as ToDoApplication).kodein.instance()
    private var preDeletedList: ListModel? = null
    val listModel: LiveData<List<ListModel>>

    init {
        listModel = dao.getAll()
    }

    fun addItem(item: ListModel) = dao.add(item)

    fun deleteItem(id: Int) {
        preDeletedList = dao.getById(id)
        dao.delete(preDeletedList!!)
    }

    fun undoDelete() {
        preDeletedList.let {
            dao.add(it!!)
        }
        preDeletedList = null
    }
}