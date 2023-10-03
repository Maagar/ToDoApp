package com.example.todoapp.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineDispatcher

class TaskListViewModelFactory(
    private val application: Application,
    private val repository: TaskListRepository,
    private val ioDispatcher: CoroutineDispatcher
):ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            return TaskListViewModel(application, repository, ioDispatcher) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}

class TaskViewModelFactory(
    private val application: Application,
    private val repository: TaskRepository,
    private val ioDispatcher: CoroutineDispatcher
):ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(application, repository, ioDispatcher) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}