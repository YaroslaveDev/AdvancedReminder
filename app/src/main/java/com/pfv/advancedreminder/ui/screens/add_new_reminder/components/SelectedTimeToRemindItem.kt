package com.pfv.advancedreminder.ui.screens.add_new_reminder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pfv.advancedreminder.R

@Composable
fun SelectedTimeToRemindItem(
    canDelete: Boolean,
    time: String,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = time,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            IconButton(
                onClick = onEdit
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "img",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            if (canDelete){
                IconButton(
                    onClick = onDelete
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "img",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}