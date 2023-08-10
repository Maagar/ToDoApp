package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.todoapp.data.AppDatabase
import com.example.todoapp.data.TaskListRepository
import com.example.todoapp.data.TaskListViewModel
import com.example.todoapp.data.TaskListViewModelFactory
import com.example.todoapp.ui.AppScreen
import com.example.todoapp.ui.components.FullScreenDialog
import com.example.todoapp.ui.theme.ToDoAppTheme
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val repository = TaskListRepository(AppDatabase.getDatabase(applicationContext).taskListDao(), Dispatchers.IO)
        val viewModel by viewModels<TaskListViewModel>() {
            TaskListViewModelFactory(application, repository, Dispatchers.IO)
        }
        super.onCreate(savedInstanceState)
        setContent {
            val currentList = remember { mutableStateOf(0) }
            val showDialog = rememberSaveable { mutableStateOf(false) }
            ToDoAppTheme {
                AppScreen(showDialog = showDialog, viewModel = viewModel, currentList = currentList)
                FullScreenDialog(showDialog = showDialog, viewModel = viewModel, currentList = currentList)
            }

        }
    }
}