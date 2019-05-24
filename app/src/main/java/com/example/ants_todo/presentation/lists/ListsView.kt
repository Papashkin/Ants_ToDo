package com.example.ants_todo.presentation.lists

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.presentation.common.fragment.BaseFragment
import com.example.ants_todo.presentation.lists.adapter.ListsAdapter
import com.example.ants_todo.presentation.lists.adapter.ListsSwipeCallback
import com.example.ants_todo.util.navigation.Screens
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.lists_fragment.*

class ListsView : BaseFragment() {

    private lateinit var viewModel: ListsViewModel

    private var listsAdapter: ListsAdapter = ListsAdapter(
        onItemClick = {
            router.navigateTo(Screens.ToDosScreen(it))
        },
        onItemDelete = { id, name ->
            viewModel.deleteItem(id)
            showSnackBar(name)
        }
    )

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

        viewModel.listModel.observe(this, Observer {
            listsAdapter.submitList(it)
        })
    }

    private fun setAdapter() {
        val swipeHelper = ListsSwipeCallback(listsAdapter)
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
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_new_item, null)
        val insertedText = dialogView.findViewById<EditText>(R.id.etListName)

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.lists_new_list_creation))
            .setPositiveButton(getString(R.string.Ok)) { dialog, _ ->
                val name = insertedText.text
                if (name.isEmpty()) {
                    showToast(R.string.invalid_data)
                } else {
                    addItem(name.toString())
                    dialog.dismiss()
                }
            }
            .setNegativeButton(getString(R.string.Cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setView(dialogView)
            .create()
            .show()
    }

    private fun addItem(name: String) {
        viewModel.addItem(
            ListModel(name = name)
        )
    }

    private fun showSnackBar(listName: String) {
        Snackbar
            .make(listsLayout, getString(R.string.lists_snackbar_message, listName), Snackbar.LENGTH_LONG)
            .setActionTextColor(Color.YELLOW)
            .setAction(getString(R.string.undo)) {
                viewModel.undoDeleting()
            }
            .show()
    }
}