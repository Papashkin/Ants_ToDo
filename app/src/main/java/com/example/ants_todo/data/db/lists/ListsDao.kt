package com.example.ants_todo.data.db.lists

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ants_todo.data.models.ListModel

@Dao
interface ListsDao {

    @Query("SELECT * from lists")
    fun getAll(): LiveData<List<ListModel>>

    @Query("Select * from lists where name = :name")
    fun getByName(name: String): ListModel

    @Query("Select * from lists where id = :id")
    fun getById(id: Int): ListModel

    @Insert
    fun add(list: ListModel)

    @Update
    fun update(list: ListModel)

    @Delete
    fun delete(list: ListModel)
}