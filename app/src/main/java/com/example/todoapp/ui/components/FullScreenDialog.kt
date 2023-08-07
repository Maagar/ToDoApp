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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.todoapp.R
import com.example.todoapp.data.TaskList
import com.example.todoapp.data.TaskListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialog(showDialog: MutableState<Boolean>, viewModel: TaskListViewModel) {
    var text by rememberSaveable { mutableStateOf("") }
    if(showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = !showDialog.value },
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
                        IconButton(onClick = { showDialog.value = !showDialog.value }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.dialog_close)
                            )
                        }
                        Text(
                            text = stringResource(R.string.create_new_list),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 8.dp, start = 6.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(onClick = { addNewList(text, showDialog, viewModel) }) {
                            Text(text = stringResource(R.string.done))
                        }
                    }
                    Divider()
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = { Text(text = "Enter list title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                    )
                    Divider()
                }

            }
        }
    }
}

fun addNewList(text: String, showDialog: MutableState<Boolean>, viewModel: TaskListViewModel) {
    viewModel.addTaskList(TaskList(name = text))
    showDialog.value = !showDialog.value
}