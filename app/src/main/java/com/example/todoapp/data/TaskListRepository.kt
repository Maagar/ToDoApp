package com.example.todoapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TaskListRepository(private val taskListDao: TaskListDao, private val ioDispatcher: CoroutineDispatcher) {
    val readAllData: LiveData<List<TaskList>> = taskListDao.readAllTaskListData()
    suspend fun getLatestTaskList(): TaskList? {
        return withContext(ioDispatcher) {
            taskListDao.getLatestTaskList()
        }
    }
    suspend fun addTaskList(taskList: TaskList) {
        taskListDao.insertTaskList(taskList)
    }
}