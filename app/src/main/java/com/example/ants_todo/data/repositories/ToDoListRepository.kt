package com.example.ants_todo.data.repositories

import com.example.ants_todo.data.ToDoList
import com.example.ants_todo.data.lists.ToDoListDao

class ToDoListRepository(private val dao: ToDoListDao) {

    fun getAll() = dao.getAll()

    fun getListByName(name: String) = dao.getByName(name)

    fun insert(list: ToDoList) = dao.add(list)

    fun delete(list: ToDoList) = dao.delete(list)

    fun update(list: ToDoList) = dao.update(list)

}