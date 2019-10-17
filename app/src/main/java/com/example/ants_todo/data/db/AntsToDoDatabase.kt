package com.example.ants_todo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ants_todo.data.db.lists.ListsDao
import com.example.ants_todo.data.db.toDo.ToDoDao
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.models.ToDoModel

@Database(
    entities = [ListModel::class, ToDoModel::class],
    version = 1,
    exportSchema = false
)
abstract class AntsToDoDatabase: RoomDatabase() {
    abstract fun listDao(): ListsDao
    abstract fun toDoDao(): ToDoDao
}