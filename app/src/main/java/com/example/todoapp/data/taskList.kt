package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class TaskList(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val listPlace: Int
)