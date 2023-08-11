package com.example.todoapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(
    application: Application,
    private val repository: TaskListRepository,
    private val ioDispatcher: CoroutineDispatcher
): AndroidViewModel(application) {
    val readAllData: LiveData<List<TaskList>>
    init {
        val taskListDao = AppDatabase.getDatabase(application).taskListDao()
        readAllData = repository.readAllData
    }

    fun addTaskList(taskList: TaskList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTaskList(taskList)
        }
    }
    fun readAllData(): LiveData<List<TaskList>> {
        return repository.readAllData
    }
    suspend fun fetchLatestTaskList(): TaskList? {
        return repository.getLatestTaskList()
    }
    suspend fun fetchTaskList(id: Int): TaskList? {
        return repository.getTaskList(id)
    }
    fun updateTaskList(taskList: TaskList) {
        viewModelScope.launch {
            repository.updateTaskList(taskList)
        }
    }
    fun deleteTaskList(taskList: TaskList) {
        viewModelScope.launch {
            repository.deleteTaskList(taskList)
        }
    }
}