package com.example.todoapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.data.Task

@Dao
interface taskListDao {
    @Insert
    fun insertTaskList(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM Task WHERE TASK.id = :listId ORDER BY tastText ASC")
    fun getTasks(listId: Int): List<Task>


}