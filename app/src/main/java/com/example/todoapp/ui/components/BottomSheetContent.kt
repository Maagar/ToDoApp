package com.example.todoapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.TaskList
import com.example.todoapp.data.TaskListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    sheetState: ModalBottomSheetState,
    showListBottomSheet: MutableState<Boolean>,
    showSortBottomSheet: MutableState<Boolean>,
    showSettingsBottomSheet: MutableState<Boolean>,
    title: MutableState<String>,
    viewModel: TaskListViewModel,
    currentList: MutableState<Int>
){
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.inverseOnSurface,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            val showDialog = remember { mutableStateOf(false) }
            FullScreenDialog(showDialog, viewModel, currentList, title)
            if (showListBottomSheet.value) {
            }
            else if (showSortBottomSheet.value) {
            }
            else if (showSettingsBottomSheet.value) {
                SettingsBottomSheet(showDialog, title, viewModel, currentList, scope)
            }
        }) {

        }
}

@Composable
fun SettingsBottomSheet(
    showDialog: MutableState<Boolean>,
    title: MutableState<String>,
    viewModel: TaskListViewModel,
    currentList: MutableState<Int>,
    scope: CoroutineScope
) {
    val tempTitle = stringResource(R.string.rename_list_title)
    var tempTaskList: TaskList = TaskList(name = "")
    LaunchedEffect(Unit) {
        scope.launch {
            tempTaskList = viewModel.fetchTaskList(currentList.value)
        }
    }
    val name = tempTaskList.name
    LaunchedEffect(Unit) {
        viewModel.fetchTaskList(currentList.value)
    }
    FullScreenDialog(showDialog, viewModel, currentList, title, name = name)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        TextButton(onClick = {
            title.value = tempTitle
            showDialog.value = !showDialog.value
        }) {
            Text(text = stringResource(R.string.rename_list))
        }
    }
}