package com.example.ants_todo.util.adapter

interface AntsBaseViewHolder<T: Any> {
    fun bind(item: T, position: Int)
}