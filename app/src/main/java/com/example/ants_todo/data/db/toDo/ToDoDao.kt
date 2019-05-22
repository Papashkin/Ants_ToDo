package com.example.ants_todo.data.db.toDo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ants_todo.data.models.ToDoModel

@Dao
interface ToDoDao {

    @Query("SELECT * from toDos where listId = :listId")
    fun getToDos(listId: Int): LiveData<List<ToDoModel>>

    @Query("Select * from toDos where name = :name")
    fun getByName(name: String): ToDoModel

    @Query("Select * from toDos where id = :id")
    fun getById(id: Int): ToDoModel

    @Insert
    fun add(list: ToDoModel)

    @Update
    fun update(list: ToDoModel)

    @Delete
    fun delete(list: ToDoModel)
}