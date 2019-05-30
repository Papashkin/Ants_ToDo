package com.example.ants_todo.data.repositories

import com.example.ants_todo.data.db.lists.ListsDao
import com.example.ants_todo.data.models.ListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListsRepository(private val dao: ListsDao) {

    suspend fun getAllAsync() = withContext(Dispatchers.IO) {
        dao.getAll()
    }

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