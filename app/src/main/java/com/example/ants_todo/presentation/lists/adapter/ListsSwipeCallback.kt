package com.example.ants_todo.presentation.lists.adapter

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.R

class ListsSwipeCallback(private val todoAdapter: ListsAdapter) : ItemTouchHelper.Callback() {

    private lateinit var backgroundColor: ColorDrawable

    private var iconDelete: Drawable? = null
    private var iconMargin: Int = -1
    private var iconTop: Int = -1
    private var iconBottom: Int = -1
    private val backgroundCornerOffset = 20 // background is behind the rounded corners of itemView

    override fun getMovementFlags(view: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlags = ItemTouchHelper.LEFT
        val dragFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean = false

    override fun isItemViewSwipeEnabled(): Boolean = true

    override fun onMove(
        view: RecyclerView,
        vHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = true

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
        iconDelete = ContextCompat.getDrawable(itemView.context, R.drawable.ic_delete_white_30)
        backgroundColor = ColorDrawable(ContextCompat.getColor(itemView.context, R.color.color_red_700))

        setIconPosition(itemView, iconDelete)

        val background: ColorDrawable = setBackgroundDrawable(dX, itemView)

        background.draw(c)
        iconDelete?.draw(c)
    }

    private fun setBackgroundDrawable(dX: Float, view: View): ColorDrawable =
        when {
            (dX < 0) -> {
                val iconLeft = view.right - iconMargin - (iconDelete?.intrinsicHeight ?: 0)
                val iconRight = view.right - iconMargin
                iconDelete?.setBounds(iconLeft, iconTop, iconRight, iconBottom)

                backgroundColor.setBounds(
                    view.right + dX.toInt() - backgroundCornerOffset,
                    view.top,
                    view.right,
                    view.bottom
                )
                backgroundColor
            }
            else -> {
                backgroundColor.setBounds(0, 0, 0, 0)
                backgroundColor
            }
        }

    private fun setIconPosition(view: View, icon: Drawable?) {
        iconMargin = (view.height - (icon?.intrinsicHeight ?: 0)) / 5
        iconTop = view.top + (view.height - (icon?.intrinsicHeight ?: 0)) / 2
        iconBottom = iconTop + (icon?.intrinsicHeight ?: 0)
    }
}