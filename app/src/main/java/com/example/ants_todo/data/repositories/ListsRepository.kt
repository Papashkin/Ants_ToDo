package com.example.ants_todo.data.repositories

import com.example.ants_todo.data.db.lists.ListsDao
import com.example.ants_todo.data.models.ListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ListsRepository(private val dao: ListsDao) {

    fun getAllAsync() = CoroutineScope(Dispatchers.IO).async {
        dao.getAll()
    }

    fun getByNameAsync(name: String) = CoroutineScope(Dispatchers.IO).async {
        dao.getByName(name)
    }

    fun getListByIdAsync(id: Int) = CoroutineScope(Dispatchers.IO).async {
        dao.getById(id)
    }

    fun insertAsync(list: ListModel) = CoroutineScope(Dispatchers.IO).async {
        dao.add(list)
    }

    fun deleteAsync(list: ListModel) = CoroutineScope(Dispatchers.IO).async {
        dao.delete(list)
    }

}