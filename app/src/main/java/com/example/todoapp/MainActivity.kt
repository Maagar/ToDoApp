package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.todoapp.data.TaskListViewModel
import com.example.todoapp.ui.AppScreen
import com.example.todoapp.ui.components.FullScreenDialog
import com.example.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<TaskListViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            val showDialog = rememberSaveable { mutableStateOf(false) }
            ToDoAppTheme {
                AppScreen(showDialog = showDialog, viewModel = viewModel)
                FullScreenDialog(showDialog, viewModel)
            }

        }
    }
}