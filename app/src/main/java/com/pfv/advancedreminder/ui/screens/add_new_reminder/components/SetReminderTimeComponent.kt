package com.pfv.advancedreminder.ui.screens.add_new_reminder.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.ui.anim.PulsatingAnimation
import com.pfv.advancedreminder.ui.screens.add_new_reminder.form.AddNewReminderScreenForm

@Composable
fun SetReminderTimeComponent(
    form: AddNewReminderScreenForm,
    needAddNewTime: Boolean,
    modifier: Modifier,
    time: List<String>,
    onEditByIndex: (Int) -> Unit,
    onDeleteByIndex: (Int) -> Unit,
    onAddNewTime: () -> Unit
) {


    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {


        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.select_time),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (form.hasSelectTimeToRemindError()) MaterialTheme.colorScheme.error else
                            MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .animateContentSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    time.forEachIndexed { index, item ->

                        SelectedTimeToRemindItem(
                            canDelete = (index > 0) || (time.size > 1),
                            time = item,
                            onEdit = {
                                onEditByIndex(index)
                            },
                            onDelete = {
                                onDeleteByIndex(index)
                            }
                        )
                    }

                    if (needAddNewTime) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            PulsatingAnimation(
                                pulsarColor = MaterialTheme.colorScheme.primary,
                                fab = {
                                    IconButton(
                                        modifier = Modifier,
                                        onClick = onAddNewTime
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_add),
                                            contentDescription = "img",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }

            if (form.hasSelectTimeToRemindError()){
                Text(
                    text = stringResource(id = form.selectTimeToRemindError!!, form.countOfAdditionalTimesToRemind()),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}