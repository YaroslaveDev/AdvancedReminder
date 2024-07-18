package com.pfv.advancedreminder.ui.screens.add_new_reminder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pfv.advancedreminder.constants.DaysOfWeek

@Composable
fun SelectableDayOfWeekItem(
    isSelected: Boolean,
    modifier: Modifier,
    dayOfWeek: DaysOfWeek,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ){

        Text(
            modifier = Modifier
                .padding(vertical = 12.dp),
            text = stringResource(id = dayOfWeek.text),
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }

}