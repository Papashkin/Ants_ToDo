package com.example.ants_todo.data.db.lists

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ants_todo.data.models.ListModel


@Database(entities = [ListModel::class], version = 1, exportSchema = false)
abstract class ListsDatabase : RoomDatabase() {
    abstract fun listDao(): ListsDao
}