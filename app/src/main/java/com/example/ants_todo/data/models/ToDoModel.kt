package com.example.ants_todo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "toDos")
data class ToDoModel(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val isChecked: Boolean,
    val listId: Int
)