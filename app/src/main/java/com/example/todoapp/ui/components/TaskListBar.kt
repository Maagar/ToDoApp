package com.example.todoapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.TaskList
import com.example.todoapp.data.TaskListViewModel

@Composable
fun TaskListBar(
    modifier: Modifier = Modifier,
    showDialog: MutableState<Boolean>,
    viewModel: TaskListViewModel,
    currentList: MutableState<Int>
    ) {
    var taskLists by remember { mutableStateOf(emptyList<TaskList>()) }
    
    LaunchedEffect(viewModel.readAllData) {
        viewModel.readAllData().observeForever {newTaskList ->
            taskLists = newTaskList
        }
    }
    LazyRow(modifier = modifier) {
        item {
            IconButton(
                onClick = { currentList.value = 0}
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null)
            }
        }
        items(taskLists) {taskList ->
            TextButton(onClick = { currentList.value = taskList.id }) {
                Text(
                    text = taskList.name,
                    color = MaterialTheme.colorScheme.onBackground
                    )
            }
        }
        item {
            TextButton(onClick = { showDialog.value = !showDialog.value }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, Modifier.size(18.dp))
                Text(text = stringResource(R.string.new_list))
            }
        }
    }
    Divider()
}