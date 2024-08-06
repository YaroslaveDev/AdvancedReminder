package com.pfv.advancedreminder.ui.screens.add_new_reminder.ui_state

import com.pfv.advancedreminder.constants.TimeType
import java.util.Date

sealed class AddNewReminderUiState {

    object InitState : AddNewReminderUiState()
    object AddNewTime : AddNewReminderUiState()
    data class EditTimeByIndex(val index: Int, val time: Date) : AddNewReminderUiState()
    object AddNewDate : AddNewReminderUiState()
    data class SetNewStartEndTime(val type: TimeType) : AddNewReminderUiState()

}