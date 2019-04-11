package com.example.ants_todo.data.lists

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ants_todo.data.ToDoList

@Database(entities = [ToDoList::class], version = 1, exportSchema = false)
abstract class ToDoListDatabase(): RoomDatabase() {
    abstract fun todoDao(): ToDoListDao
}