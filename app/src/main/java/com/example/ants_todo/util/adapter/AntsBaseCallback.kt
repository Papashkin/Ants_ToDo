package com.example.ants_todo.util.adapter

import androidx.recyclerview.widget.DiffUtil

abstract class AntsBaseCallback<T : Any> : DiffUtil.Callback() {
    var oldList: List<T> = listOf()
    var newList: List<T> = listOf()

    fun setLists(oldList: List<T>, newList: List<T>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getNewListSize(): Int = this.newList.size

    override fun getOldListSize(): Int = this.oldList.size
}