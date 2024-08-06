package com.pfv.advancedreminder.ui.screens.home

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.ext.date.toFormattedString
import com.pfv.advancedreminder.ext.numbers.ifNegativeSetZero
import com.pfv.advancedreminder.ui.navigation.routes.Screens
import com.pfv.advancedreminder.ui.screens.home.components.DateItem
import com.pfv.advancedreminder.ui.screens.home.components.InfoBoardItem
import com.pfv.advancedreminder.ui.screens.home.event.HomeScreenEvent
import com.pfv.advancedreminder.ui.screens.home.nav_state.HomeScreenNavState
import java.util.Calendar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val currentDate by remember {
        mutableStateOf(Calendar.getInstance().time)
    }
    val initialPageIndex by remember {
        derivedStateOf {
            viewModel.dates.indexOfFirst {
                it.toFormattedString() == Calendar.getInstance().time.toFormattedString()
            }.ifNegativeSetZero()
        }
    }
    val pagerState = rememberPagerState(
        initialPage = initialPageIndex
    ) {
        viewModel.dates.size
    }

    val lazyState = rememberLazyListState()

    val centerPosition by remember {
        derivedStateOf {
            val visibleInfo = lazyState.layoutInfo.visibleItemsInfo
            if (visibleInfo.isEmpty()) -1
            else {
                val offset = (visibleInfo.last().index - visibleInfo.first().index) / 2

                visibleInfo.first().index + offset
            }
        }
    }

    LaunchedEffect(Unit) {

        lazyState.animateScrollToItem(
            centerPosition
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.reduceEvent(HomeScreenEvent.NavToAddNewReminderScreen)
                }
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = ""
                )
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    ),
                text = currentDate
                    .toString()
                    .uppercase(),
                style = MaterialTheme.typography.titleLarge
                    .copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp
                    ),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                lineHeight = 42.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoBoardItem(
                    modifier = Modifier,
                    info = "0",
                    title = "Total"
                )

                InfoBoardItem(
                    modifier = Modifier,
                    info = "0",
                    title = "Active"
                )
            }

            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                state = pagerState,
                pageSize = PageSize.Fixed(74.dp),
                snapPosition = SnapPosition.Center,
                pageSpacing = 20.dp,
                key = {
                    it
                },
            ) {

                val itemDate by remember {
                    derivedStateOf {
                        viewModel.dates[it]
                    }
                }

                DateItem(
                    isActiveItem = it == pagerState.currentPage,
                    date = itemDate
                )
            }
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