package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskListDao {
    @Insert
    suspend fun insertTaskList(taskList: TaskList)

    @Delete
    suspend fun deleteTaskList(taskList: TaskList)

    @Update
    suspend fun updateTaskList(taskList: TaskList)
    @Query("SELECT * FROM TaskList ORDER BY creation_date DESC LIMIT 1")
    suspend fun getLatestTaskList(): TaskList?

    @Query("SELECT * FROM TaskList ORDER BY creation_date ASC")
    fun readAllTaskListData(): LiveData<List<TaskList>>

    @Query("SELECT * FROM TaskList WHERE id = :id")
    fun getTaskList(id: Int): Flow<TaskList?>
}