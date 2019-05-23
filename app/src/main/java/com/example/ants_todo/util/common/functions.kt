package com.example.ants_todo.util.common

import android.app.Activity
import android.app.Dialog
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.ants_todo.R
import kotlinx.android.synthetic.main.dialog_add_new_item.view.*

fun showDialog(activity: Activity, text: String = ""): Dialog?  {
    if (!activity.isFinishing) {
        val contentView = View.inflate(activity, R.layout.dialog_add_new_item, null)
        val dialog = AlertDialog.Builder(activity).create()

        dialog.show()
        dialog.setContentView(contentView)
        contentView.etListName.hint = text
        contentView.etListName.requestFocus()
        return dialog
    }
    return null
}