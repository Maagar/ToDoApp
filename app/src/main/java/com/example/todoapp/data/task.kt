package com.example.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val listId: Int,
    val taskText: String,
    val details: String = "",
    val star: Boolean,
    val completed: Boolean,
    @ColumnInfo(name = "creation_date")
    val creationDate: Long = System.currentTimeMillis(),
)