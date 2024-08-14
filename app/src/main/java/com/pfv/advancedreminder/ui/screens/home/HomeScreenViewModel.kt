package com.pfv.advancedreminder.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pfv.advancedreminder.tools.getDatesFromCurrentDateRange
import com.pfv.advancedreminder.ui.screens.add_new_reminder.constants.ReminderType
import com.pfv.advancedreminder.ui.screens.home.event.HomeScreenEvent
import com.pfv.advancedreminder.ui.screens.home.nav_state.HomeScreenNavState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    var dates = mutableStateListOf<Date>().apply {
        addAll(getDatesFromCurrentDateRange())
    }
    var navState by mutableStateOf<HomeScreenNavState>(HomeScreenNavState.InitState)
    private var _selectedReminderType = MutableStateFlow(ReminderType.BASE)
    var selectedReminderType: StateFlow<ReminderType> = _selectedReminderType.asStateFlow()

   fun reduceEvent(event: HomeScreenEvent){

       when(event){
           HomeScreenEvent.NavToAddNewReminderScreen -> {
               navState = HomeScreenNavState.NavToAddNewReminderScreen
           }

           is HomeScreenEvent.UpdateReminderType -> updateReminderType(event.type)
       }
   }

    private fun updateReminderType(type: ReminderType){
        viewModelScope.launch {
            _selectedReminderType.emit(type)
        }
    }

    fun resetNavState(){
        navState = HomeScreenNavState.InitState
    }
}