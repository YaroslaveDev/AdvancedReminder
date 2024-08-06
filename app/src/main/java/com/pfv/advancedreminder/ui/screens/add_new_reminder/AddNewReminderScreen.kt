package com.pfv.advancedreminder.ui.screens.add_new_reminder

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.constants.TimeType.END
import com.pfv.advancedreminder.constants.TimeType.START
import com.pfv.advancedreminder.ext.date.toHourMinutePair
import com.pfv.advancedreminder.tools.clearKeyboardAndFocusOfField
import com.pfv.advancedreminder.ui.common.BaseAppDatePicker
import com.pfv.advancedreminder.ui.common.BaseAppTimePicker
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.ChangeReminderTypeSection
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.CountOfRemindingsComponent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.InputTitleDescriptionSection
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.SelectSimpleTimeComponent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.SelectableDaysToRemindSection
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.SetDateComponent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.components.SetReminderTimeComponent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.constants.ReminderType.BASE
import com.pfv.advancedreminder.ui.screens.add_new_reminder.constants.ReminderType.RANDOM
import com.pfv.advancedreminder.ui.screens.add_new_reminder.event.AddNewReminderEvent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.nav_state.AddNewReminderNavState
import com.pfv.advancedreminder.ui.screens.add_new_reminder.ui_state.AddNewReminderUiState
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AddNewReminderScreen(
    navController: NavController,
    viewModel: AddNewReminderScreenViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val reminderState by viewModel.selectedReminderType.collectAsState()
    val form by viewModel.form.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val navState by viewModel.navState.collectAsState()
    val context = LocalContext.current

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
                .imePadding()
                .verticalScroll(rememberScrollState())
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null
                ) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
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
                    form.startDate,
                    form.endDate
                ),
                onClick = {
                    viewModel.reduceEvent(AddNewReminderEvent.AddNewDate)
                }
            )

            if (form.needDisplaySelectSpecifiedDaysSection()) {
                SelectableDaysToRemindSection(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    selectedItems = form.selectableDaysOfWeekToRemind,
                    onItemSelect = {
                        viewModel.reduceEvent(
                            AddNewReminderEvent.UpdateSelectableDayOfWeekToRemind(
                                it
                            )
                        )
                    }
                )
            } else {
                LaunchedEffect(Unit) {
                    viewModel.resetSelectableDaysOfWeekToRemind()
                }
            }


            AnimatedContent(targetState = reminderState, label = "") {
                when(it){
                    BASE -> {
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
                            },
                            form = form
                        )
                    }
                    RANDOM -> {
                        SelectSimpleTimeComponent(
                            form = form,
                            modifier = Modifier
                                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                            setNewTime = {
                                viewModel.reduceEvent(AddNewReminderEvent.ShowPopupSetNewStartEndTime(it))
                            }
                        )
                    }
                }
            }

            InputTitleDescriptionSection(
                coroutineScope = coroutineScope,
                bringIntoViewRequester = bringIntoViewRequester,
                keyboardController = keyboardController,
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                onTitleChanged = {
                    viewModel.reduceEvent(AddNewReminderEvent.UpdateTitle(it))
                },
                onDescriptionChanged = {
                    viewModel.reduceEvent(AddNewReminderEvent.UpdateDescription(it))
                },
                focusManager = focusManager,
                form = form
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                onClick = {
                    viewModel.reduceEvent(AddNewReminderEvent.AddNewReminderClick(context = context))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(6.dp)
            ) {

                Text(
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    text = stringResource(id = R.string.add_new_reminder),
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .height(30.dp)
            )
        }
    }

    when (val state = uiState) {
        AddNewReminderUiState.AddNewTime -> {

            val pairOfHourAndMinute = Calendar.getInstance().time.toHourMinutePair()
            clearKeyboardAndFocusOfField(keyboardController, focusManager)

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
            clearKeyboardAndFocusOfField(keyboardController, focusManager)

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

            clearKeyboardAndFocusOfField(keyboardController, focusManager)

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

        is AddNewReminderUiState.SetNewStartEndTime -> {

            clearKeyboardAndFocusOfField(keyboardController, focusManager)

            val pairOfHourAndMinute = when(state.type){
                START -> form.startTimeToRemind.toHourMinutePair()
                END -> form.endTimeToRemind.toHourMinutePair()
            }

            BaseAppTimePicker(
                onConfirm = {
                    viewModel.reduceEvent(
                        AddNewReminderEvent.SetNewStartEndTime(state.type, it)
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
    }

    LaunchedEffect(navState) {

        when (navState) {
            AddNewReminderNavState.InitState -> {}
            AddNewReminderNavState.NavigateBack -> {
                navController.navigateUp()
            }
        }
    }
}