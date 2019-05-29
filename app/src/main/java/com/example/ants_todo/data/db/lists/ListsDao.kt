package com.example.ants_todo.data.db.lists

import androidx.room.*
import com.example.ants_todo.data.models.ListModel

@Dao
interface ListsDao {

    @Query("SELECT * from lists")
    suspend fun getAll(): List<ListModel>

    @Query("Select * from lists where id = :id")
    suspend fun getById(id: Int): ListModel

    @Insert
    suspend fun add(list: ListModel)

    @Delete
    suspend fun delete(list: ListModel)
}