package com.example.ants_todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todoLists")
class ToDoList {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    private var name: String = ""


    fun getName(): String = this.name

    fun getId(): Int = this.id

    fun setName(name: String) {
        this.name = name
    }

    fun setId(id: Int) {
        this.id = id
    }

}


