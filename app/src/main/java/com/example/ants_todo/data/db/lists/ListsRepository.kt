package com.example.ants_todo.data.db.lists

import com.example.ants_todo.data.db.lists.ListsDao
import com.example.ants_todo.data.models.ListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ListsRepository(private val dao: ListsDao) {
    private val scope = CoroutineScope(Dispatchers.IO)

    fun getAllAsync() = scope.async { dao.getAll() }

    fun getByNameAsync(name: String) = scope.async { dao.getByName(name) }

    fun getListByIdAsync(id: Int) = scope.async { dao.getById(id) }

    fun insertAsync(list: ListModel) = scope.async { dao.insert(list) }

    fun deleteAsync(list: ListModel) = scope.async { dao.delete(list) }

}