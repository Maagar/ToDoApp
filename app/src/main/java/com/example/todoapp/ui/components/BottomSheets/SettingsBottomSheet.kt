package com.example.todoapp.ui.components.BottomSheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.TaskListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsBottomSheet(
    showDialog: MutableState<Boolean>,
    title: MutableState<String>,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    settingsDialog: MutableState<Boolean>,
    viewModel: TaskListViewModel,
    currentList: MutableState<Int>
) {
    if (!sheetState.isVisible) {
        settingsDialog.value = false
    }
    title.value = stringResource(R.string.rename_list_title)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                showDialog.value = !showDialog.value
                scope.launch {
                    sheetState.hide()
                    settingsDialog.value = !settingsDialog.value
                }
            }) {
            Text(
                text = stringResource(R.string.rename_list),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            ) }
        val enabled = remember { mutableStateOf(false) }
        enabled.value = currentList.value != 1
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled.value,
            onClick = {
                scope.launch {
                    sheetState.hide()
                    settingsDialog.value = !settingsDialog.value
                    val taskListToDelete = viewModel.fetchTaskList(currentList.value)
                    if (taskListToDelete != null) {
                        viewModel.deleteTaskList(taskListToDelete)
                        currentList.value = 1
                    }
                }
            }) {
            Text(
                text = stringResource(R.string.delete_list),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            ) }
    }
}