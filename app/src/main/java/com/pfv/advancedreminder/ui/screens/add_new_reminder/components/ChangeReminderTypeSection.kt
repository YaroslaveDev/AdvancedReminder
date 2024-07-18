package com.pfv.advancedreminder.ui.screens.add_new_reminder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.ui.screens.add_new_reminder.constants.ReminderType
import com.pfv.advancedreminder.ui.screens.add_new_reminder.event.AddNewReminderEvent

@Composable
fun ChangeReminderTypeSection(
    reminderType: ReminderType,
    modifier: Modifier,
    onEvent: (AddNewReminderEvent) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        ReminderTypeComponent(
            modifier = Modifier
                .weight(1f),
            isSelected = reminderType == ReminderType.BASE,
            text = stringResource(id = R.string.base),
            onClick = {
                onEvent(AddNewReminderEvent.ChangeReminderType(ReminderType.BASE))
            }
        )

        ReminderTypeComponent(
            modifier = Modifier
                .weight(1f),
            isSelected = reminderType != ReminderType.BASE,
            text = stringResource(id = R.string.random),
            onClick = {
                onEvent(AddNewReminderEvent.ChangeReminderType(ReminderType.RANDOM))
            }
        )
    }
}