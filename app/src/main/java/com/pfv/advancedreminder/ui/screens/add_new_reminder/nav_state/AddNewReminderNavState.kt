package com.pfv.advancedreminder.ui.screens.add_new_reminder.nav_state

sealed class AddNewReminderNavState {

    object InitState : AddNewReminderNavState()
    object NavigateBack : AddNewReminderNavState()
}