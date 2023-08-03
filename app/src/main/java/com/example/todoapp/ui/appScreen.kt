package com.example.todoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.ui.components.Task
import com.example.todoapp.ui.components.TaskListBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    val topBarColor = colors.background
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(topBarColor)
    Scaffold(
        bottomBar = {
                    BottomAppBar {
                        Row(modifier = modifier.padding(horizontal = 12.dp)) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.baseline_list_alt_24),
                                    contentDescription = stringResource(R.string.your_lists)
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.baseline_swap_vert_24),
                                    contentDescription = stringResource(R.string.sort)
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.baseline_more_horiz_24),
                                    contentDescription = stringResource(R.string.manage)
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
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TaskListBar()
            Task(text = "Test")
        }
    }
}