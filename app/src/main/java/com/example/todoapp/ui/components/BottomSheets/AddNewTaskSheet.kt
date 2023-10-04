package com.example.todoapp.ui.components.BottomSheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun AddNewTaskSheet(
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    isAddingNewTask: MutableState<Boolean>,
    viewModel: TaskViewModel,
    currentList: MutableState<Int>
) {
    if (!sheetState.isVisible) {
        isAddingNewTask.value = false
    }
    val text = remember { mutableStateOf(TextFieldValue("")) }
    val details = remember { mutableStateOf(TextFieldValue("")) }
    val isAddingDetails = remember { mutableStateOf(false) }
    val keyboard = LocalSoftwareKeyboardController.current
    val focusRequester = remember {
        FocusRequester()
    }
    val isStarred = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusTarget()
                    .focusRequester(focusRequester),
                value = text.value,
                onValueChange = {text.value = it},
                placeholder = { Text(text = stringResource(R.string.new_task))}
            )
            if (isAddingDetails.value) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = details.value,
                    onValueChange = {details.value = it},
                    placeholder = { Text(text = stringResource(R.string.add_details))}
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val isTextFieldEmpty = (text.value.text != "")
                IconButton(onClick = { isAddingDetails.value = !isAddingDetails.value }) {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_notes_24), contentDescription = stringResource(R.string.add_details))
                }
                IconButton(onClick = { isStarred.value = !isStarred.value }) {
                    if (isStarred.value) {
                        Icon(Icons.Default.Star, contentDescription = stringResource(R.string.unstar_this_task))
                    } else {
                        Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_star_border_24), contentDescription = stringResource(R.string.star_this_task))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    enabled = isTextFieldEmpty,
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = stringResource(R.string.done))
                }
            }
            LaunchedEffect(focusRequester) {
                if (isAddingNewTask.value) {
                    focusRequester.requestFocus()
                    delay(100)
                    keyboard?.show()
                    val endOfText = TextRange(text.value.text.length)
                    text.value = TextFieldValue(text.value.text, endOfText)
                }
            }
        }
    }

}