package com.example.todoapp.dao

import androidx.room.Delete
import androidx.room.Insert
import com.example.todoapp.data.Task

interface taskDao {
    @Delete
    fun deleteTask(task: Task)

    @Insert
    fun insertTask(task: Task)
}