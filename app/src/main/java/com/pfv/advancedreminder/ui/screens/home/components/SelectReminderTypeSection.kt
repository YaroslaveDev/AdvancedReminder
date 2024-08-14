package com.pfv.advancedreminder.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.ui.screens.add_new_reminder.constants.ReminderType

@Composable
fun SelectReminderTypeSection(
    modifier: Modifier,
    state: ReminderType,
    onUpdateReminderType: (ReminderType) -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextButton(
            modifier = Modifier
                .weight(1f),
            onClick = {
                onUpdateReminderType(ReminderType.BASE)
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = if (state == ReminderType.BASE)
                    MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.base_reminder),
                    fontSize = 16.sp
                )

                if (state == ReminderType.BASE) {
                    HorizontalDivider(
                        modifier = Modifier
                            .width(10.dp)
                            .padding(top = 2.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }else{
                    HorizontalDivider(
                        modifier = Modifier
                            .width(10.dp)
                            .padding(top = 2.dp)
                            .alpha(0f),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        TextButton(
            modifier = Modifier
                .weight(1f),
            onClick = {
                onUpdateReminderType(ReminderType.RANDOM)
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = if (state == ReminderType.RANDOM)
                    MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.random_reminder),
                    fontSize = 16.sp
                )
                if (state == ReminderType.RANDOM) {
                    HorizontalDivider(
                        modifier = Modifier
                            .width(10.dp)
                            .padding(top = 2.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }else{
                    HorizontalDivider(
                        modifier = Modifier
                            .width(10.dp)
                            .padding(top = 2.dp)
                            .alpha(0f),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}