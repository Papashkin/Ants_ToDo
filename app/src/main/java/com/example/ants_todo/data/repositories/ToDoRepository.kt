package com.example.ants_todo.data.repositories

import com.example.ants_todo.data.db.toDo.ToDoDao
import com.example.ants_todo.data.models.ToDoModel

class ToDoRepository(private val dao: ToDoDao) {

    fun getToDos(id: Int) = dao.getToDos(id)

    fun getByName(name: String) = dao.getByName(name)

    fun getById(id: Int) = dao.getById(id)

    fun insert(list: ToDoModel) = dao.add(list)

    fun delete(list: ToDoModel) = dao.delete(list)

    fun update(list: ToDoModel) = dao.update(list)

}