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

    fun getByIdAsync(id: Int) = CoroutineScope(Dispatchers.IO).async {
        dao.getById(id)
    }

    fun getByNameAndListIdAsync(name: String, listId: Int) = CoroutineScope(Dispatchers.IO).async {
        dao.getByNameAndListId(name, listId)
    }
    fun getByNameAsync(name: String) = CoroutineScope(Dispatchers.IO).async {
        dao.getByName(name)
    }

    fun insertAsync(list: ToDoModel) = CoroutineScope(Dispatchers.IO).async {
        dao.add(list)
    }

    fun deleteAsync(list: ToDoModel) = CoroutineScope(Dispatchers.IO).async {
        dao.delete(list)
    }

    fun updateAsync(list: ToDoModel) = CoroutineScope(Dispatchers.IO).async {
        dao.update(list)
    }

    fun updateAllAsync(listId: Int) = CoroutineScope(Dispatchers.IO).async {
        dao.updateAll(listId, false)
    }

}