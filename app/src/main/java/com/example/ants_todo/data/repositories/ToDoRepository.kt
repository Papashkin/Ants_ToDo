package com.example.ants_todo.data.repositories

import com.example.ants_todo.data.db.toDo.ToDoDao
import com.example.ants_todo.data.models.ToDoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ToDoRepository(private val dao: ToDoDao) {

    fun getToDosAsync(id: Int) = CoroutineScope(Dispatchers.IO).async {
        dao.getToDos(id)
    }

    fun getById(id: Int) = CoroutineScope(Dispatchers.IO).async {
        dao.getById(id)
    }

    fun insert(list: ToDoModel) = CoroutineScope(Dispatchers.IO).async {
        dao.add(list)
    }

    fun delete(list: ToDoModel) = CoroutineScope(Dispatchers.IO).async {
        dao.delete(list)
    }

    fun update(list: ToDoModel) = CoroutineScope(Dispatchers.IO).async {
        dao.update(list)
    }

}