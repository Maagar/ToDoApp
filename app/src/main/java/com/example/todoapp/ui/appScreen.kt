package com.example.todoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.TaskListViewModel
import com.example.todoapp.ui.components.TaskListBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier, showDialog: MutableState<Boolean>, viewModel: TaskListViewModel) {
    val colors = MaterialTheme.colorScheme
    val topBarColor = colors.background
    val bottomBarColor = colors.tertiary
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(topBarColor)
    systemUiController.setNavigationBarColor(bottomBarColor)
    val currentList = remember { mutableStateOf(0) }
    val listShowDialog = remember { mutableStateOf(false) }
    val sortShowDialog = remember { mutableStateOf(false) }
    val settingsShowDialog = remember { mutableStateOf(false) }


    Box (
        modifier = modifier.fillMaxSize()

    ){
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    Row(modifier = modifier.padding(horizontal = 12.dp)) {
                        IconButton(onClick = { listShowDialog.value = !listShowDialog.value }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.baseline_list_alt_24),
                                contentDescription = stringResource(R.string.your_lists),
                                Modifier.alpha(0.8f)
                            )
                        }
                        IconButton(onClick = { sortShowDialog.value = !sortShowDialog.value }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.baseline_swap_vert_24),
                                contentDescription = stringResource(R.string.sort),
                                Modifier.alpha(0.8f)
                            )
                        }
                        if (currentList.value != 0) {
                            IconButton(onClick = { settingsShowDialog.value = !settingsShowDialog.value }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.baseline_more_horiz_24),
                                    contentDescription = stringResource(R.string.manage),
                                    Modifier.alpha(0.8f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        FloatingActionButton(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(R.string.add_new_task_list)
                            )
                        }
                    }
                }
            },
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    title = {
                        Text(
                            text = stringResource(R.string.top_bar_title),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues = it)
            ) {
                TaskListBar(showDialog = showDialog, viewModel = viewModel, currentList = currentList)
            }
        }
    }
}