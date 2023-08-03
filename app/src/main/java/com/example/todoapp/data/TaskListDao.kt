package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface taskListDao {
    @Insert
    suspend fun insertTaskList(taskList: TaskList)

    @Delete
    suspend fun deleteTaskList(taskList: TaskList)

    @Update
    suspend fun updateTaskList(taskList: TaskList)

    @Query("SELECT * FROM TaskList ORDER BY name ASC")
    fun readAllTaskListData(): LiveData<List<TaskList>>

    @Query("SELECT * FROM TaskList WHERE id = :id")
    fun getTaskList(id: Int): Flow<TaskList?>
    @Query("SELECT MAX(listPlace) FROM TaskList WHERE id = :id")
    fun getLastPlace(id: Int): Int
}