package com.example.todoapp.data

import kotlinx.coroutines.CoroutineDispatcher

class TaskRepository(private val taskDao: TaskDao, private val ioDispatcher: CoroutineDispatcher) {

}