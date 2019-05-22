package com.example.ants_todo.data.db.toDo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ants_todo.data.models.ToDoModel


@Database(entities = [ToDoModel::class], version = 1, exportSchema = false)
abstract class ToDosDatabase : RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}