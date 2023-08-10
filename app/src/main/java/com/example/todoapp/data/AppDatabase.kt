package com.example.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Task::class, TaskList::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): taskDao
    abstract fun taskListDao(): TaskListDao
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
                )
                    .addCallback(AppDatabaseCallback(context))
                    .build()
                
                Instance = instance
                return instance
            }
        }
    }

}

class AppDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val taskListDao = AppDatabase.getDatabase(context).taskListDao()
        CoroutineScope(Dispatchers.IO).launch {
            taskListDao.insertTaskList(TaskList(id = 1, name = context.getString(R.string.my_tasks)))
        }
    }
}


