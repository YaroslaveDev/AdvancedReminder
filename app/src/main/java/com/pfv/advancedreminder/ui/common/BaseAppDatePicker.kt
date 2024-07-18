package com.pfv.advancedreminder.ui.common

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.ext.date.longToDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppDatePicker(
    startDate: Date?,
    endDate: Date?,
    onConfirm: (Date?, Date?) -> Unit,
    onDismiss: () -> Unit
) {

    val datePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = startDate?.time,
        initialSelectedEndDateMillis = endDate?.time
    )

    DatePickerDialog(
        onDismissRequest = {
        },
        confirmButton = {
            TextButton(onClick = {

                onConfirm(
                    datePickerState.selectedStartDateMillis?.longToDate(),
                    datePickerState.selectedEndDateMillis?.longToDate()
                )
            }) {
                Text(
                    text = stringResource(id = R.string.ok),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        content = {
            DateRangePicker(
                state = datePickerState,
                title = null,
                headline = null,
                showModeToggle = false
            )
        },

        )
}