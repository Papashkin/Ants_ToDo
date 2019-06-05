package com.example.ants_todo.data.db.toDo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ants_todo.data.models.ToDoModel

@Dao
interface ToDoDao {

    @Query("SELECT * from toDos where listId = :listId")
    fun getToDos(listId: Int): LiveData<List<ToDoModel>>

    @Query("Select * from toDos where name = :name and listId = :listId")
    suspend fun getByNameAndListId(name: String, listId: Int): ToDoModel

    @Query("Select * from toDos where id = :id")
    suspend fun getById(id: Int): ToDoModel

    @Insert
    suspend fun add(list: ToDoModel)

    @Update
    suspend fun update(list: ToDoModel)

    @Query("Update toDos set isChecked = :isChecked")
    suspend fun updateAll(isChecked: Boolean)

    @Delete
    suspend fun delete(list: ToDoModel)
}