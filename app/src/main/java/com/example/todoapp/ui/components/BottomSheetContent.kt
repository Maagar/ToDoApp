package com.example.todoapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.TaskListViewModel
import com.example.todoapp.data.TaskViewModel
import com.example.todoapp.ui.components.BottomSheets.AddNewTaskSheet
import com.example.todoapp.ui.components.BottomSheets.SettingsBottomSheet
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    sheetState: ModalBottomSheetState,
    showListBottomSheet: MutableState<Boolean>,
    showSortBottomSheet: MutableState<Boolean>,
    showSettingsBottomSheet: MutableState<Boolean>,
    showAddNewTaskSheet: MutableState<Boolean>,
    taskListViewModel: TaskListViewModel,
    taskViewModel: TaskViewModel,
    currentList: MutableState<Int>,
    scope: CoroutineScope
){
    val showDialog = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf("") }
    FullScreenDialog(showDialog, taskListViewModel, currentList, title, isChanging = true)
    ModalBottomSheetLayout(
        sheetGesturesEnabled = false,
        sheetState = sheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.inverseOnSurface,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            if (showListBottomSheet.value) {
            }
            else if (showSortBottomSheet.value) {
            }
            else if (showSettingsBottomSheet.value) {
                SettingsBottomSheet(showDialog, title, sheetState, scope,
                    showSettingsBottomSheet, taskListViewModel, currentList)
            }
            else if (showAddNewTaskSheet.value) {
                AddNewTaskSheet(
                    sheetState = sheetState,
                    scope = scope,
                    isAddingNewTask = showAddNewTaskSheet,
                    viewModel = taskViewModel,
                    currentList = currentList
                )
            }
        }
    ) {

        }
}