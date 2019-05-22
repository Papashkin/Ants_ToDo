package com.example.ants_todo.presentation.lists.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.presentation.lists.viewModel.ListsViewModel
import com.example.ants_todo.presentation.lists.adapter.ItemSwipeCallback
import com.example.ants_todo.presentation.lists.adapter.ListsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.lists_fragment.*
import kotlin.random.Random

class ListsView : Fragment() {

    private lateinit var viewModel: ListsViewModel
    private lateinit var listsAdapter: ListsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.lists_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setListeners()

        viewModel.listModel.observeForever {
            listsAdapter.submitList(it)
        }
    }

    private fun setAdapter() {
        listsAdapter = ListsAdapter(
            onItemClick = {
                Toast.makeText(requireContext(), "list № $it clicked", Toast.LENGTH_SHORT).show()
            },
            onItemDelete = { id, name ->
                viewModel.deleteItem(id)
                showSnackBar(name)
            }
        )
        val swipeHelper = ItemSwipeCallback(listsAdapter)
        val touchHelper = ItemTouchHelper(swipeHelper)
        touchHelper.attachToRecyclerView(listsRecycler)
        listsRecycler.adapter = listsAdapter
    }

    private fun setListeners() {
        btnAddList.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_new_list, null)
        val insertedText = dialogView.findViewById<EditText>(R.id.etListName)

        AlertDialog.Builder(requireContext())
            .setTitle("New list creation")
            .setPositiveButton("Ok") { dialog, _ ->
                val name = insertedText.text
                if (name.isEmpty()) {
                    Toast.makeText(requireContext(), "empty data!", Toast.LENGTH_SHORT).show()
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
            ListModel(
                id = Random.nextInt(),
                name = name
            )
        )
    }

    private fun showSnackBar(listName: String) {
        Snackbar
            .make(toDoListsLayout, "ListModel \"$listName\" was deleted", Snackbar.LENGTH_LONG)
            .setActionTextColor(Color.YELLOW)
            .setAction("UNDO") {
                viewModel.undoDelete()
            }
            .show()
    }

    override fun onPause() {
        super.onPause()
        viewModel.listModel.removeObserver { }
    }
}