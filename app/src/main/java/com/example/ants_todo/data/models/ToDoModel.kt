package com.example.ants_todo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "toDos")
data class ToDoModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    var isChecked: Boolean,
    val listId: Int
)