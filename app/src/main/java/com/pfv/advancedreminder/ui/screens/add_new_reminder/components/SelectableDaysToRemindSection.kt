package com.pfv.advancedreminder.ui.screens.add_new_reminder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.constants.DaysOfWeek

@Composable
fun SelectableDaysToRemindSection(
    modifier: Modifier,
    selectedItems: List<DaysOfWeek>,
    onItemSelect: (DaysOfWeek) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {


        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.days_to_remind, selectedItems.size),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            DaysOfWeek.entries.forEach{

                SelectableDayOfWeekItem(
                    modifier = Modifier
                        .weight(1f),
                    dayOfWeek = it,
                    isSelected = it in selectedItems,
                    onClick = {
                        onItemSelect(it)
                    }
                )
            }
        }
    }
}