package com.pfv.advancedreminder.ui.navigation.routes

import kotlinx.serialization.Serializable


sealed class Screens {

    @Serializable
    data object Splash : Screens()
    @Serializable
    data object Home : Screens()
    @Serializable
    data object AddNewReminder : Screens()
}