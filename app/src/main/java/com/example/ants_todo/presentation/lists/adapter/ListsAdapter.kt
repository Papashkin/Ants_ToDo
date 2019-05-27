package com.example.ants_todo.presentation.lists.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.databinding.ListsViewBinding


class ListsAdapter(
    private val onItemClick: (list: ListModel) -> Unit,
    private val onItemDelete: (id: Int, name: String) -> Unit
) : ListAdapter<ListModel, ListsAdapter.ListViewHolder2>(ListsItemCallback()) {

    private val itemListener = object : OnListsItemClickListener {
        override fun onClick(item: ListModel) {
            onItemClick.invoke(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ListViewHolder2 {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListsViewBinding>(inflater, R.layout.lists_view, parent, false)
        return ListViewHolder2(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder2, pos: Int) {
        viewHolder.getBinding().list = getItem(pos)
        viewHolder.getBinding().listener = itemListener
        viewHolder.getBinding().executePendingBindings()
    }

    fun deleteItem(position: Int) {
        val item = getItem(position)
        onItemDelete.invoke(item.id, item.name)
    }

    inner class ListViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ListsViewBinding = DataBindingUtil.bind(itemView)!!

        fun getBinding() = binding
    }
}