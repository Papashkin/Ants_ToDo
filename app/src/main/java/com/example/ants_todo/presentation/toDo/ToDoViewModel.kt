package com.example.ants_todo.presentation.toDo

import androidx.lifecycle.*
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.data.repositories.ToDoRepository
import com.example.ants_todo.presentation.ToDoApplication
import com.example.ants_todo.presentation.common.fragment.BaseViewModel
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.erased.instance


class ToDoViewModel(private val listId: Int, private val listName: String) : BaseViewModel() {

    override val kodein: Kodein = ToDoApplication.getKodein()

    private val toDoRepository: ToDoRepository by instance()
    private var preDeletedToDo: ToDoModel? = null

    val isExisted: MutableLiveData<Boolean>
    val toDos: LiveData<List<ToDoModel>>
    init {
        toDos = liveData {
            emitSource(toDoRepository.getToDosAsync(listId).await())
        }
        isExisted = MutableLiveData(false)
    }

    fun addItem(item: ToDoModel) = viewModelScope.launch {
        val existedOne: ToDoModel? = toDoRepository.getByNameAndListIdAsync(item.name, item.listId).await()
        if (existedOne != null) {
            isExisted.postValue(true)
        } else {
            toDoRepository.insertAsync(item).await()
        }
    }

    fun deleteItem(id: Int) = viewModelScope.launch {
        preDeletedToDo = toDoRepository.getByIdAsync(id).await()
        toDoRepository.deleteAsync(preDeletedToDo!!).await()
    }

    fun updateItem(id: Int) = viewModelScope.launch {
        val updatedItem = toDoRepository.getByIdAsync(id).await()
        updatedItem.isChecked = !updatedItem.isChecked
        toDoRepository.updateAsync(updatedItem).await()
    }

    fun undoDeleting() = viewModelScope.launch {
        toDoRepository.insertAsync(preDeletedToDo!!).await()
        preDeletedToDo = null
    }

    fun uncheckAll() = viewModelScope.launch {
        toDoRepository.updateAllAsync().await()
    }

    fun getListName(): String = this.listName
}