package com.pfv.advancedreminder.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.pfv.advancedreminder.R
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppTimePicker(
    hour: Int,
    minute: Int,
    onConfirm: (Date) -> Unit,
    onDismiss: () -> Unit
) {

    val state = rememberTimePickerState(
        initialHour = hour,
        initialMinute = minute
    )

    AlertDialog(
        title = {
            TimePicker(state = state)
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {

            TextButton(onClick = {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, state.hour)
                cal.set(Calendar.MINUTE, state.minute)
                cal.isLenient = false

                onConfirm(cal.time)
            }) {
                Text(
                    text = stringResource(id = R.string.ok),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    )
}