package com.example.todoapp.data

import androidx.lifecycle.LiveData

class TaskListRepository(private val taskListDao: taskListDao) {
    val readAllData: LiveData<List<TaskList>> = taskListDao.readAllTaskListData()
    suspend fun addTaskList(taskList: TaskList) {
        taskListDao.insertTaskList(taskList)
    }
}