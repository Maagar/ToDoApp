package com.example.todoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.TaskListViewModel
import com.example.todoapp.ui.components.FullScreenDialog
import com.example.todoapp.ui.components.TaskListBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AppScreen(
    showDialog: MutableState<Boolean>,
    viewModel: TaskListViewModel,
    currentList: MutableState<Int>,
    sheetState: ModalBottomSheetState,
    showListBottomSheet: MutableState<Boolean>,
    showSortBottomSheet: MutableState<Boolean>,
    showSettingsBottomSheet: MutableState<Boolean>,
    title: MutableState<String>
) {
    val colors = MaterialTheme.colorScheme
    val topBarColor = colors.background
    val bottomBarColor = colors.tertiary
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(topBarColor)
    systemUiController.setNavigationBarColor(bottomBarColor)
    val scope = rememberCoroutineScope()
    Box (
        modifier = Modifier.fillMaxSize()

    ){
        Scaffold(
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
            },
            content = {
                FullScreenDialog(showDialog, viewModel, currentList, title)
                Column(
                    modifier = Modifier
                        .padding(paddingValues = it)
                ) {
                    TaskListBar(showDialog, viewModel, currentList, title)
                }
            },
            bottomBar = {
                BottomAppBar {
                    Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                        IconButton(onClick = { showListBottomSheet.value = !showListBottomSheet.value }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.baseline_list_alt_24),
                                contentDescription = stringResource(R.string.your_lists),
                                Modifier.alpha(0.8f)
                            )
                        }
                        IconButton(onClick = { showSortBottomSheet.value = !showSortBottomSheet.value }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.baseline_swap_vert_24),
                                contentDescription = stringResource(R.string.sort),
                                Modifier.alpha(0.8f)
                            )
                        }
                        if (currentList.value != 0) {
                            IconButton(onClick = {
                                showSettingsBottomSheet.value = !showSettingsBottomSheet.value
                                scope.launch {
                                    sheetState.show()
                                }
                            }) {
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
            }
        )
    }
}