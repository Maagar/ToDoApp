package com.example.todoapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<TaskList>>
    private val repository: TaskListRepository

    init {
        val taskListDao = AppDatabase.getDatabase(application).taskListDao()
        repository = TaskListRepository(taskListDao)
        readAllData = repository.readAllData
    }

    fun addTaskList(taskList: TaskList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTaskList(taskList)
        }
    }
}