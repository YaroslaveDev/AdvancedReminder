package com.pfv.advancedreminder.ui.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.pfv.advancedreminder.tools.getDatesFromCurrentDateRange
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    var dates = mutableStateListOf<Date>().apply {
        addAll(getDatesFromCurrentDateRange())
    }

//    fun fillInitialData(){
//
//        if (dates.isEmpty()){
//            dates.addAll(
//                getDatesFromCurrentDateRange()
//            )
//        }
//    }
}