package com.pfv.advancedreminder.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pfv.advancedreminder.tools.getDatesFromCurrentDateRange
import com.pfv.advancedreminder.ui.screens.home.event.HomeScreenEvent
import com.pfv.advancedreminder.ui.screens.home.nav_state.HomeScreenNavState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    var dates = mutableStateListOf<Date>().apply {
        addAll(getDatesFromCurrentDateRange())
    }
    var navState by mutableStateOf<HomeScreenNavState>(HomeScreenNavState.InitState)

   fun reduceEvent(event: HomeScreenEvent){

       when(event){
           HomeScreenEvent.NavToAddNewReminderScreen -> {
               navState = HomeScreenNavState.NavToAddNewReminderScreen
           }
       }
   }

    fun resetNavState(){
        navState = HomeScreenNavState.InitState
    }
}