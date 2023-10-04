package com.example.todoapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.todoapp.data.AppDatabase
import com.example.todoapp.data.TaskListRepository
import com.example.todoapp.data.TaskListViewModel
import com.example.todoapp.data.TaskListViewModelFactory
import com.example.todoapp.data.TaskRepository
import com.example.todoapp.data.TaskViewModel
import com.example.todoapp.data.TaskViewModelFactory
import com.example.todoapp.ui.AppScreen
import com.example.todoapp.ui.components.BottomSheet
import com.example.todoapp.ui.theme.ToDoAppTheme
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val taskListRepository = TaskListRepository(AppDatabase.getDatabase(applicationContext).taskListDao(), Dispatchers.IO)
        val taskRepository = TaskRepository(AppDatabase.getDatabase(applicationContext).taskDao(), Dispatchers.IO)
        val taskListViewModel by viewModels<TaskListViewModel>() {
            TaskListViewModelFactory(application, taskListRepository, Dispatchers.IO)
        }
        val taskViewModel by viewModels<TaskViewModel>() {
            TaskViewModelFactory(application, taskRepository, Dispatchers.IO)
        }
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            val currentList = remember { mutableStateOf(0) }
            val showDialog = rememberSaveable { mutableStateOf(false) }
            val title = rememberSaveable { mutableStateOf("") }
            val sheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden
            )
            val showListBottomSheet = remember { mutableStateOf(false) }
            val showSortBottomSheet = remember { mutableStateOf(false) }
            val showSettingsBottomSheet = remember { mutableStateOf(false) }
            val showAddNewTaskSheet = remember { mutableStateOf(false) }
            if (sheetState.currentValue != ModalBottomSheetValue.Hidden) {
                DisposableEffect(Unit) {
                    onDispose {
                        showSettingsBottomSheet.value = false
                    }
                }
            }
            ToDoAppTheme {
                AppScreen(showDialog, taskListViewModel, taskViewModel, currentList, sheetState,
                    showListBottomSheet, showSortBottomSheet, showSettingsBottomSheet, title,
                    showAddNewTaskSheet)
                BottomSheet(sheetState, showListBottomSheet, showSortBottomSheet,
                    showSettingsBottomSheet, showAddNewTaskSheet, taskListViewModel, taskViewModel,
                    currentList, scope)
            }
        }
    }
}