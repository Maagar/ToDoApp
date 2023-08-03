package com.example.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Task::class, TaskList::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): taskDao
    abstract fun taskListDao(): taskListDao
    companion object {
        @Volatile
        private var Instance: AppDatabase? = null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = Instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(lock = this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                Instance = instance
                return instance
            }
        }
    }

}
