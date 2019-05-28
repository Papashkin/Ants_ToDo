package com.example.ants_todo.presentation.lists

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
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
import kotlinx.android.synthetic.main.add_list_button_motion.*
import kotlinx.android.synthetic.main.lists_fragment.*
import kotlinx.android.synthetic.main.lists_fragment.mlAddNewList

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
    private val transitionListener = object : MotionLayout.TransitionListener {
        override fun onTransitionChange(layout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
        override fun onTransitionTrigger(layout: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
        override fun onTransitionStarted(layout: MotionLayout?, p1: Int, p2: Int) {}
        override fun allowsTransition(p0: MotionScene.Transition?): Boolean = true
        override fun onTransitionCompleted(layout: MotionLayout?, currentId: Int) {
            when (currentId) {
                layout?.endState -> {
                    etNewList.requestFocus()
                    showKeyboard()
                }
                layout?.startState -> {
                    hideKeyboard()
                }
            }
        }
    }

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
        (mlAddNewList as MotionLayout).setTransitionListener(transitionListener)

        btnAddItem.setOnClickListener {
            val newName = etNewList.text.toString()
            checkNewListName(newName)
        }

        etNewList.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                checkNewListName(etNewList.text.toString())
            }
            true
        }
    }

    private fun checkNewListName(name: String) {
        if (name.isNotEmpty()) {
            etNewList.text.clear()
            (mlAddNewList as MotionLayout).transitionToStart()
            addItem(name)
        } else {
            showToast(getString(R.string.invalid_data))
        }
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

    override fun onResume() {
        super.onResume()
        (mlAddNewList as MotionLayout).transitionToStart()
    }
}