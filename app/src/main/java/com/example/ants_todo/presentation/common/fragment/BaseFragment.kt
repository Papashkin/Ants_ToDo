package com.example.ants_todo.presentation.common.fragment

import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.ants_todo.presentation.ToDoApplication
import com.pawegio.kandroid.inputMethodManager
import com.pawegio.kandroid.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import ru.terrakok.cicerone.Router

abstract class BaseFragment(override val kodein: Kodein = ToDoApplication.getKodein()): Fragment(), KodeinAware {

    val router: Router by instance()

    fun showToast(text: String) {
        toast(text)
    }

    fun showToast(textId: Int) {
        toast(textId)
    }

    fun showKeyboard() {
        val inputMethodManager = activity!!.inputMethodManager
        inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun hideKeyboard() {
        val inputMethodManager = activity!!.inputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}