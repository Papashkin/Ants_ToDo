package com.example.ants_todo.presentation.toDo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.presentation.toDo.adapter.ToDoAdapter
import com.example.ants_todo.presentation.toDo.adapter.ToDoSwipeCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.todo_fragment.*

class ToDoView : Fragment() {
    fun newInstance(list: ListModel): ToDoView {
        val fragment = ToDoView()
        val args = Bundle()
        fragment.listId = list.id
        fragment.listName = list.name
        fragment.arguments = args
        return fragment
    }

    private lateinit var viewModel: ToDoViewModel
    private lateinit var toDoModelFactory: ToDoModelFactory

    private var listId: Int = -1
    private var listName: String = ""
    private var toDoAdapter = ToDoAdapter(
        onItemClicked = { id ->
            viewModel.updateItem(id)
        },
        onItemDeleted = { name, id ->
            viewModel.deleteItem(id)
            showSnackBar(name)
        },
        onItemEdit = {

        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        toDoModelFactory = ToDoModelFactory(listId)
        viewModel = ViewModelProviders.of(this, toDoModelFactory).get(ToDoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.todo_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toDosToolbar.title = listName.toUpperCase()

        setListeners()
        setAdapter()

        viewModel.toDos.observe(this, Observer {
            toDoAdapter.setList(it as ArrayList<ToDoModel>)
        })

    }

    private fun setListeners() {
        btnAddItem.setOnClickListener {
            showAddDialog()
        }
    }

    private fun setAdapter() {
        val swipeCallback = ToDoSwipeCallback(toDoAdapter)
        val touchHelper = ItemTouchHelper(swipeCallback)
        touchHelper.attachToRecyclerView(toDosRecycler)
        toDosRecycler.adapter = toDoAdapter
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_new_item, null)
        val insertedText = dialogView.findViewById<EditText>(R.id.etListName)
        insertedText.hint = "Please, insert the item name"
        insertedText.requestFocus()

        AlertDialog.Builder(requireContext())
            .setTitle("New item creation")
            .setPositiveButton("Ok") { dialog, _ ->
                val name = insertedText.text
                if (name.isEmpty()) {
                    Toast.makeText(requireContext(), "empty data!", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                } else {
                    addItem(name.toString())
                    dialog.dismiss()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setView(dialogView)
            .create()
            .show()
    }

    private fun addItem(name: String) {
        viewModel.addItem(
            ToDoModel(
                name = name,
                isChecked = false,
                listId = listId
            )
        )
    }

    private fun showSnackBar(name: String) {
        Snackbar
            .make(toDosLayout, "List \"$name\" was deleted", Snackbar.LENGTH_LONG)
            .setActionTextColor(Color.YELLOW)
            .setAction("UNDO") {
                viewModel.undoDeleting()
            }
            .show()
    }
}