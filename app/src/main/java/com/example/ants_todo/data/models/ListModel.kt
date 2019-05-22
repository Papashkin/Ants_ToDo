package com.example.ants_todo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lists")
data class ListModel(
    @PrimaryKey var id: Int = 0,
    var name: String
)


