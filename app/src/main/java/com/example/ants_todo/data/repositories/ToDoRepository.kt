package com.example.ants_todo.data.repositories

import com.example.ants_todo.data.db.toDo.ToDoDao
import com.example.ants_todo.data.models.ToDoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDoRepository(private val dao: ToDoDao) {

    suspend fun getToDos(id: Int) = withContext(Dispatchers.IO) {
        dao.getToDos(id)
    }

    suspend fun getById(id: Int) = withContext(Dispatchers.IO) {
        dao.getById(id)
    }

    suspend fun insert(list: ToDoModel) = withContext(Dispatchers.IO) {
        dao.add(list)
    }

    suspend fun delete(list: ToDoModel) = withContext(Dispatchers.IO) {
        dao.delete(list)
    }

    suspend fun update(list: ToDoModel) = withContext(Dispatchers.IO) {
        dao.update(list)
    }

}