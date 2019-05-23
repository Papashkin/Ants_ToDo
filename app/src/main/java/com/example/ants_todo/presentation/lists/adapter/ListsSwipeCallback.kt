package com.example.ants_todo.presentation.lists.adapter

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.R
import com.example.ants_todo.presentation.lists.adapter.ListsAdapter

class ListsSwipeCallback(private val todoAdapter: ListsAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(view: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlags = ItemTouchHelper.LEFT
        val dragFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean = false

    override fun isItemViewSwipeEnabled(): Boolean = true

    override fun onMove(view: RecyclerView, vHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        todoAdapter.deleteItem(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 20     // background is behind the rounded corners of itemView

//        val iconDelete = itemView.context.resources.getDrawable(R.drawable.ic_delete_white_30)
        val iconDelete = ContextCompat.getDrawable(itemView.context, R.drawable.ic_delete_white_30)

//        val bgRed = ColorDrawable(itemView.context.resources.getColor(R.color.color_white))
        val bgRed = ColorDrawable(ContextCompat.getColor(itemView.context, R.color.color_red_700))

        val iconMargin = (itemView.height - (iconDelete?.intrinsicHeight ?: 0)) / 5
        val iconTop = itemView.top + (itemView.height - (iconDelete?.intrinsicHeight ?: 0)) / 2
        val iconBottom = iconTop + (iconDelete?.intrinsicHeight ?: 0)

        val background: ColorDrawable
        background = when {
            (dX < 0) -> {   // delete swipe
                val iconLeft = itemView.right - iconMargin - (iconDelete?.intrinsicHeight ?: 0)
                val iconRight = itemView.right - iconMargin
                iconDelete?.setBounds(iconLeft, iconTop, iconRight, iconBottom)

                bgRed.setBounds(
                    itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
                bgRed
            }
            else -> {
                bgRed.setBounds(0, 0, 0, 0)
                bgRed
            }
        }

        background.draw(c)
        iconDelete?.draw(c)
    }
}