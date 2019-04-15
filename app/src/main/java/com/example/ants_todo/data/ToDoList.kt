package com.example.ants_todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todoLists")
data class ToDoList (

    @PrimaryKey
    var id: Int = 0,
    var name: String
    )


