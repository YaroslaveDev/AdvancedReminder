package com.pfv.advancedreminder.ui.screens.add_new_reminder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.ext.date.toHourMinutePair
import com.pfv.advancedreminder.ui.common.BaseAppDatePicker
import com.pfv.advancedreminder.ui.common.BaseAppTimePicker
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.ChangeReminderTypeSection
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.CountOfRemindingsComponent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.SelectableDaysToRemindSection
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.SetDateComponent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.SetReminderTimeComponent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.event.AddNewReminderEvent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.ui_state.AddNewReminderUiState
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewReminderScreen(
    navController: NavController,
    viewModel: AddNewReminderScreenViewModel = hiltViewModel()
) {

    val timePickerState = rememberTimePickerState(
        is24Hour = true
    )

    val reminderState by viewModel.selectedReminderType.collectAsState()
    val form by viewModel.form.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {

            CenterAlignedTopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = stringResource(id = R.string.add_new_reminder),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "img",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ChangeReminderTypeSection(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(it),
                reminderType = reminderState,
                onEvent = viewModel::reduceEvent,
            )

            CountOfRemindingsComponent(
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                incrementValue = {
                    viewModel.reduceEvent(AddNewReminderEvent.IncrementTimesToRemind)
                },
                decrementValue = {
                    viewModel.reduceEvent(AddNewReminderEvent.DecrementTimesToRemind)
                },
                value = form.timesToRemind
            )

            SetDateComponent(
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                date = Pair(
                    form.startDate ?: Calendar.getInstance().time,
                    form.endDate ?: Calendar.getInstance().time
                ),
                onClick = {
                    viewModel.reduceEvent(AddNewReminderEvent.AddNewDate)
                }
            )

            if (form.needDisplaySelectSpecifiedDaysSection()){
                SelectableDaysToRemindSection(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    selectedItems = form.selectableDaysOfWeekToRemind,
                    onItemSelect = {
                        viewModel.reduceEvent(AddNewReminderEvent.UpdateSelectableDayOfWeekToRemind(it))
                    }
                )
            }else{
                LaunchedEffect(Unit){
                    viewModel.resetSelectableDaysOfWeekToRemind()
                }
            }

            SetReminderTimeComponent(
                needAddNewTime = (form.timesToRemind > 1) && (form.timesToRemind > form.time.size),
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                time = form.formattedTimeList(),
                onEditByIndex = {
                    viewModel.reduceEvent(
                        AddNewReminderEvent.ShowTimePicker_EditTime(
                            it,
                            form.getTimeByIndex(it)
                        )
                    )
                },
                onDeleteByIndex = {
                    viewModel.reduceEvent(AddNewReminderEvent.RemoveTimeByIndex(it))
                },
                onAddNewTime = {
                    viewModel.reduceEvent(AddNewReminderEvent.ShowTimePicker_AddTime)
                }
            )
        }
    }

    when (val state = uiState) {
        AddNewReminderUiState.AddNewTime -> {

            val pairOfHourAndMinute = Calendar.getInstance().time.toHourMinutePair()

            BaseAppTimePicker(
                onConfirm = {
                    viewModel.reduceEvent(
                        AddNewReminderEvent.AddNewTime(it)
                    )
                    viewModel.resetUiState()
                },
                onDismiss = {
                    viewModel.resetUiState()
                },
                hour = pairOfHourAndMinute.first,
                minute = pairOfHourAndMinute.second
            )
        }

        is AddNewReminderUiState.EditTimeByIndex -> {

            val pairOfHourAndMinute = state.time.toHourMinutePair()

            BaseAppTimePicker(
                onConfirm = {
                    viewModel.reduceEvent(
                        AddNewReminderEvent.UpdateTimeByIndex(it, state.index)
                    )
                    viewModel.resetUiState()
                },
                onDismiss = {
                    viewModel.resetUiState()
                },
                hour = pairOfHourAndMinute.first,
                minute = pairOfHourAndMinute.second
            )
        }

        AddNewReminderUiState.InitState -> {}
        AddNewReminderUiState.AddNewDate -> {

            BaseAppDatePicker(
                startDate = form.startDate,
                endDate = form.endDate,
                onConfirm = { start, end ->

                    viewModel.reduceEvent(AddNewReminderEvent.UpdateDate(start, end))
                    viewModel.resetUiState()
                },
                onDismiss = {
                    viewModel.resetUiState()
                }
            )
        }
    }
}