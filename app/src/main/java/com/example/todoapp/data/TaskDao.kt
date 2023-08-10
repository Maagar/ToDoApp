package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM Task ORDER BY taskText ASC")
    fun getAllTasks(): LiveData<List<Task>>
    @Query("SELECT * FROM Task WHERE listId = :listId ORDER BY creation_date DESC")
    fun getListTasks(listId: Int): LiveData<List<Task>>
    @Query("SELECT * FROM Task WHERE id = :id")
    fun getTask(id: Int): Task?
}