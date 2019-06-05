package com.example.ants_todo.presentation.toDo.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.ants_todo.data.models.ToDoModel

class ToDoDiffCallback (
    private val oldList: List<ToDoModel>,
    private val newList: List<ToDoModel>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean  =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].name == newList[newItemPosition].name &&
                oldList[oldItemPosition].isChecked == newList[newItemPosition].isChecked
}