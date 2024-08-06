package com.pfv.advancedreminder.ui.navigation.routes

import com.pfv.advancedreminder.R
import kotlinx.serialization.Serializable


//sealed class Screens {
//
//    @Serializable
//    data object Splash : Screens()
//    @Serializable
//    data object Home : Screens()
//    @Serializable
//    data object AddNewReminder : Screens()
//}

@Serializable
sealed class SubGraph {

    @Serializable
    object Splash : SubGraph()

    @Serializable
     object Main : SubGraph()

}

sealed class BottomNavScreens(val route: Screens, val image: Int) {

    data object Home : BottomNavScreens(Screens.Home, R.drawable.ic_home)

    data object Calendar : BottomNavScreens(Screens.Calendar, R.drawable.ic_calendar)

    data object Dictionary : BottomNavScreens(Screens.Dictionary, R.drawable.ic_dictionary)

    companion object {
        val values: List<BottomNavScreens>
            get() = listOf(Home, Calendar, Dictionary)
    }
}

@Serializable
sealed class Screens {

    @Serializable
    data object SplashGraph : Screens()

    @Serializable
    data object Splash : Screens()

    @Serializable
    data object MainNavGraph : Screens()

    @Serializable
    data object Home: Screens()

    @Serializable
    data object Calendar: Screens()

    @Serializable
    data object Dictionary: Screens()

    @Serializable
    data object AddNewReminder : Screens()

}