package com.example.ants_todo.data.lists

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ants_todo.data.ToDoList

@Dao
interface ToDoListDao {

    @Query("SELECT * from todoLists")
    fun getAll(): LiveData<List<ToDoList>>

    @Query("Select * from todoLists where name = :name")
    fun getByName(name: String): ToDoList

    @Query("Select * from todoLists where id = :id")
    fun getById(id: Int): ToDoList

    @Insert
    fun add(list: ToDoList)

    @Update
    fun update(list: ToDoList)

    @Delete
    fun delete(list: ToDoList)
}