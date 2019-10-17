package com.example.ants_todo.util.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AntsViewHolder<T :Any>(view: View) : RecyclerView.ViewHolder(view), AntsBaseViewHolder<T>