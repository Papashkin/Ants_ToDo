package com.example.ants_todo.presentation.toDo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.presentation.common.fragment.BaseFragment
import com.example.ants_todo.presentation.toDo.adapter.ToDoAdapter
import com.example.ants_todo.presentation.toDo.adapter.ToDoSwipeCallback
import com.example.ants_todo.util.deleteExtraBlanks
import com.google.android.material.snackbar.Snackbar
import com.pawegio.kandroid.runDelayed
import kotlinx.android.synthetic.main.todo_fragment.*

class ToDoView : BaseFragment() {
    fun newInstance(listId: Int, listName: String): ToDoView {
        val fragment = ToDoView()
        val args = Bundle()
        fragment.listId = listId
        fragment.listName = listName
        fragment.arguments = args
        return fragment
    }

    private lateinit var viewModel: ToDoViewModel
    private lateinit var toDoModelFactory: ToDoModelFactory
    private lateinit var menu: PopupMenu

    private var snackBar: Snackbar? = null
    private var listId: Int = -1
    private var listName: String = ""
    private var toDoAdapter = ToDoAdapter(
        onItemClicked = { id ->
            viewModel.updateItem(id)
        },
        onItemDeleted = { name, id ->
            viewModel.deleteItem(id)
            showSnackBar(name)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toDoModelFactory = ToDoModelFactory(listId, listName)
        viewModel = ViewModelProviders.of(this, toDoModelFactory).get(ToDoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.todo_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvListName.text = viewModel.getListName().toUpperCase()

        setListeners()
        setAdapter()
        setObservers()
    }

    private fun setListeners() {
        ibToolbarBack.setOnClickListener {
            if (etNewItem.isFocused) {
                hideKeyboard()
                runDelayed(300) {
                    router.exit()
                }
            } else {
                router.exit()
            }
        }
        ibAddItem.setOnClickListener {
            val itemName = etNewItem.text.toString().deleteExtraBlanks()
            if (itemName.isNotEmpty()) {
                addItem(itemName)
            } else {
                showToast(R.string.invalid_data)
            }
        }
        ibMenu.setOnClickListener {
            showPopupMenu(it)
        }
    }

    private fun showPopupMenu(view: View) {
        menu = PopupMenu(this.context, view)
        menu.inflate(R.menu.menu_todo)
        menu.setOnMenuItemClickListener {
            viewModel.uncheckAll()
            true
        }
        menu.setOnDismissListener {
            it.dismiss()
        }
        menu.show()
    }

    private fun setAdapter() {
        val swipeCallback = ToDoSwipeCallback(toDoAdapter)
        val touchHelper = ItemTouchHelper(swipeCallback)
        touchHelper.attachToRecyclerView(toDosRecycler)
        toDosRecycler.adapter = toDoAdapter
    }

    private fun setObservers() {
        viewModel.toDos.observe(this, Observer {
            toDoAdapter.setList(it as ArrayList<ToDoModel>)
            etNewItem.text.clear()
            tvToDoCount.text = getString(R.string.todo_counter_text, it.filter { item -> item.isChecked }.size, it.size)
        })
        viewModel.isExisted.observe(this, Observer {
            if (it) {
                showToast(getString(R.string.todo_existed_name_message))
            }
        })
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
        snackBar = Snackbar
            .make(toDosLayout, getString(R.string.todo_snackbar_message, name), Snackbar.LENGTH_LONG)
            .setActionTextColor(Color.YELLOW)
            .setAction(getString(R.string.undo)) {
                viewModel.undoDeleting()
            }
        snackBar?.show()
    }

    override fun onPause() {
        super.onPause()
        snackBar?.dismiss()
        toDosRecycler?.recycledViewPool?.clear()
    }

}