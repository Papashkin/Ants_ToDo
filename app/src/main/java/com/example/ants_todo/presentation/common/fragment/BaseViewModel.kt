package com.example.ants_todo.presentation.common.fragment

import androidx.lifecycle.ViewModel
import com.example.ants_todo.presentation.ToDoApplication
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

abstract class BaseViewModel(override val kodein: Kodein = ToDoApplication.getKodein()): ViewModel(), KodeinAware