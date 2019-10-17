package com.example.ants_todo.presentation.lists.adapter

import android.view.View
import android.view.ViewGroup
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.util.adapter.AntsAdapter
import com.example.ants_todo.util.adapter.AntsViewHolder
import kotlinx.android.synthetic.main.card_list.view.*


class ListsAdapter(
    private val onItemClick: (list: ListModel) -> Unit,
    private val onItemDelete: (id: Int, name: String) -> Unit
) : AntsAdapter<ListsAdapter.ListsViewHolder, ListModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder =
        ListsViewHolder(inflate(parent, R.layout.card_list))

    override fun deleteItem(position: Int) {
        val item = getItem(position)
        onItemDelete.invoke(item.id, item.name)
    }

    inner class ListsViewHolder(view: View) : AntsViewHolder<ListModel>(view) {
        override fun bind(item: ListModel, position: Int) {
            itemView.tvListName.text = item.name

            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }
}