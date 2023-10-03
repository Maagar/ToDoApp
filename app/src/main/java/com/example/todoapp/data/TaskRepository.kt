package com.example.todoapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher

class TaskRepository(private val taskDao: TaskDao, private val ioDispatcher: CoroutineDispatcher) {
    val readAllData: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task) {
        taskDao.insertTask(task)
    }
}