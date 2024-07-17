package com.pfv.advancedreminder.ui.screens.home.nav_state

sealed class HomeScreenNavState {

    object InitState : HomeScreenNavState()
    object NavToAddNewReminderScreen : HomeScreenNavState()
}