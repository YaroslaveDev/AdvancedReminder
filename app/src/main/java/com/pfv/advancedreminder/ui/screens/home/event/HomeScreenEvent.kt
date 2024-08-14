package com.pfv.advancedreminder.ui.screens.home.event

import com.pfv.advancedreminder.ui.screens.add_new_reminder.constants.ReminderType

sealed class HomeScreenEvent {

    object NavToAddNewReminderScreen : HomeScreenEvent()
    data class UpdateReminderType(val type: ReminderType) : HomeScreenEvent()
}