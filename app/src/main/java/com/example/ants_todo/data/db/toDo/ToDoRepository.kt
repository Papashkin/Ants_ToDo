package com.example.ants_todo.data.db.toDo

import com.example.ants_todo.data.models.ToDoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ToDoRepository(private val dao: ToDoDao) {
    private val scope = CoroutineScope(Dispatchers.IO)

    fun getToDosAsync(id: Int) = scope.async { dao.getToDos(id) }

    fun getByIdAsync(id: Int) = scope.async { dao.getById(id) }

    fun getByNameAsync(name: String) = scope.async { dao.getByName(name) }

    fun insertAsync(list: ToDoModel) = scope.async { dao.insert(list) }

    fun deleteAsync(list: ToDoModel) = scope.async { dao.delete(list) }

    fun updateAsync(list: ToDoModel) = scope.async { dao.update(list) }

    fun updateAllAsync(listId: Int) = scope.async { dao.updateAll(listId, false) }
}