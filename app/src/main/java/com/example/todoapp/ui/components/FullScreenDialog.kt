package com.example.todoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.todoapp.R
import com.example.todoapp.data.TaskList
import com.example.todoapp.data.TaskListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FullScreenDialog(
    showDialog: MutableState<Boolean>,
    viewModel: TaskListViewModel,
    currentList: MutableState<Int>,
    title: MutableState<String>,
    isChanging: Boolean = false
    ) {
    val text = remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()
    if (isChanging) {
        getCurrentListName(coroutineScope, viewModel, currentList, text)
    }
    val keyboard = LocalSoftwareKeyboardController.current
    if(showDialog.value) {
            val focusRequester = remember { FocusRequester()
        }
        Dialog(
            onDismissRequest = {
                showDialog.value = !showDialog.value
                text.value = TextFieldValue("")
                               },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(modifier = Modifier) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        IconButton(onClick = {
                            showDialog.value = !showDialog.value
                            text.value = TextFieldValue("")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.dialog_close)
                            )
                        }
                        Text(
                            text = title.value,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 8.dp, start = 6.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            enabled = (text.value.text != ""),
                            onClick = {
                            if (isChanging) {
                                changeTaskListName(coroutineScope, viewModel, currentList, name = text.value.text)
                            }
                                else {
                                    addNewList(text, showDialog, viewModel, currentList, coroutineScope)
                            }
                            text.value = TextFieldValue("")
                            showDialog.value = false
                        }) {
                            Text(text = stringResource(R.string.done))
                        }
                    }
                    Divider()
                    TextField(
                        value = text.value,
                        onValueChange = { text.value = it },
                        placeholder = { Text(text = stringResource(R.string.list_name_placeholder)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .focusTarget()
                            .focusRequester(focusRequester)
                    )
                    LaunchedEffect(focusRequester) {
                        if (showDialog.value) {
                            focusRequester.requestFocus()
                            delay(100) // Make sure you have delay here
                            keyboard?.show()
                            val endOfText = TextRange(text.value.text.length)
                            text.value = TextFieldValue(text.value.text, endOfText)
                        }
                    }
                    Divider()
                }

            }
        }
    }
}

fun addNewList(
    text: MutableState<TextFieldValue>,
    showDialog: MutableState<Boolean>,
    viewModel: TaskListViewModel,
    currentList: MutableState<Int>,
    coroutineScope: CoroutineScope
) {
    viewModel.addTaskList(TaskList(name = text.value.text))
    showDialog.value = !showDialog.value

    coroutineScope.launch {
        val latestTaskList = viewModel.fetchLatestTaskList()
        if(latestTaskList != null) {
            currentList.value = latestTaskList.id + 1
        }
    }
}

fun getCurrentListName(scope: CoroutineScope, viewModel: TaskListViewModel, currentList: MutableState<Int>, text: MutableState<TextFieldValue>) {
    scope.launch {
        val currentTaskList = viewModel.fetchTaskList(currentList.value)
        if (currentTaskList != null) {
            text.value = TextFieldValue(currentTaskList.name)
        } else {
            // Handle the case where currentTaskList is null
        }
    }
}

fun changeTaskListName(scope: CoroutineScope, viewModel: TaskListViewModel, currentList: MutableState<Int>, name: String) {
    scope.launch {
        val currentTaskList = viewModel.fetchTaskList(currentList.value)
        val updatedTaskList = currentTaskList?.copy(name = name)
        if (updatedTaskList != null) {
            viewModel.updateTaskList(updatedTaskList)
        }
    }
}