package com.example.ants_todo.data.repositories

import com.example.ants_todo.data.db.lists.ListsDao
import com.example.ants_todo.data.models.ListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ListsRepository(private val dao: ListsDao) {

    fun getAllAsync() = CoroutineScope(Dispatchers.IO).async { dao.getAll() }

    suspend fun getListByIdAsync(id: Int) = withContext(Dispatchers.IO) {
        dao.getById(id)
    }

    suspend fun insertAsync(list: ListModel) = withContext(Dispatchers.IO) {
        dao.add(list)
    }

    suspend fun deleteAsync(list: ListModel) = withContext(Dispatchers.IO) {
        dao.delete(list)
    }

}