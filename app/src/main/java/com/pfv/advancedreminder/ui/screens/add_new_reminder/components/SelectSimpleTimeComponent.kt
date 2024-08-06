package com.pfv.advancedreminder.ui.screens.add_new_reminder.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.constants.TimeType
import com.pfv.advancedreminder.ext.date.formatDateToTimeString
import com.pfv.advancedreminder.ui.screens.add_new_reminder.form.AddNewReminderScreenForm

@Composable
fun SelectSimpleTimeComponent(
    form: AddNewReminderScreenForm,
    modifier: Modifier,
    setNewTime: (TimeType) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {


        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.select_time_range),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null
                    ) {
                        setNewTime(TimeType.START)
                    }
            ) {
                Text(
                    modifier = Modifier
                        .zIndex(2f)
                        .align(alignment = Alignment.Start)
                        .offset(x = 8.dp, y = 12.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface
                        ),
                    text = stringResource(id = R.string.start),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Box(
                    modifier = Modifier
                        .zIndex(1f)
                        .border(
                            width = 1.dp,
                            color = if (form.hastStartEndTimeError()) MaterialTheme.colorScheme.error else
                                MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .animateContentSize(),
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.Center)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Text(
                            text = form.startTimeToRemind.formatDateToTimeString(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null
                    ) {
                        setNewTime(TimeType.END)
                    }
            ) {
                Text(
                    modifier = Modifier
                        .zIndex(2f)
                        .align(alignment = Alignment.Start)
                        .offset(x = 8.dp, y = 12.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface
                        ),
                    text = stringResource(id = R.string.end),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Box(
                    modifier = Modifier
                        .zIndex(1f)
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (form.hastStartEndTimeError()) MaterialTheme.colorScheme.error else
                                MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .animateContentSize(),
                ) {

                    Row(
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Text(
                            text = form.endTimeToRemind.formatDateToTimeString(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        if (form.hastStartEndTimeError()) {
            Text(
                text = stringResource(
                    id = form.startEndTimeError!!
                ),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}