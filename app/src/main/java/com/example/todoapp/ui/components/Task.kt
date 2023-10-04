package com.example.todoapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.Task

@Composable
fun Task(
    modifier: Modifier = Modifier,
    task: Task
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center

    ) {
        RadioButton(
            selected = false,
            onClick = { /*TODO*/ }
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = task.taskText,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_star_border_24),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .alpha(0.5f)
            )
        }
    }
}