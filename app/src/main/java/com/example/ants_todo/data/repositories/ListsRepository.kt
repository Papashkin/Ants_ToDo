package com.example.ants_todo.data.repositories

import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.db.lists.ListsDao

class ListsRepository(private val dao: ListsDao) {

    fun getAll() = dao.getAll()

    fun getListByName(name: String) = dao.getByName(name)

    fun getListById(id: Int) = dao.getById(id)

    fun insert(list: ListModel) = dao.add(list)

    fun delete(list: ListModel) = dao.delete(list)

    fun update(list: ListModel) = dao.update(list)

}