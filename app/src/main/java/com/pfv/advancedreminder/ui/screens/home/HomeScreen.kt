package com.pfv.advancedreminder.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.advancedreminder.ui.navigation.routes.Screens
import com.pfv.advancedreminder.ui.screens.home.components.SelectReminderTypeSection
import com.pfv.advancedreminder.ui.screens.home.event.HomeScreenEvent
import com.pfv.advancedreminder.ui.screens.home.nav_state.HomeScreenNavState
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.rememberCalendarState

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val calendarState = rememberCalendarState()
    val reminderType by viewModel.selectedReminderType.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = it.calculateBottomPadding())
                .padding(horizontal = 16.dp)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            StaticCalendar(
                modifier = Modifier,
                monthHeader = {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 30.dp),
                        text = it.currentMonth.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                calendarState = calendarState,
                showAdjacentMonths = false,
            )

            SelectReminderTypeSection(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                state = reminderType,
                onUpdateReminderType = {
                    viewModel.reduceEvent(HomeScreenEvent.UpdateReminderType(it))
                }
            )
        }
    }

    LaunchedEffect(viewModel.navState) {

        when (viewModel.navState) {
            HomeScreenNavState.InitState -> {}
            HomeScreenNavState.NavToAddNewReminderScreen -> {
                navController.navigate(
                    Screens.MainNavGraph
                )
                viewModel.resetNavState()
            }
        }
    }
}