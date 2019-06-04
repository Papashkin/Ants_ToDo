package com.example.ants_todo.data.db.lists

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ants_todo.data.models.ListModel

@Dao
interface ListsDao {

    @Query("SELECT * from lists")
    fun getAll(): LiveData<List<ListModel>>

    @Query("Select * from lists where name = :name")
    suspend fun getByName(name: String): ListModel

    @Query("Select * from lists where id = :id")
    suspend fun getById(id: Int): ListModel

    @Insert
    suspend fun add(list: ListModel)

    @Delete
    suspend fun delete(list: ListModel)
}