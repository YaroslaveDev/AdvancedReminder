package com.pfv.advancedreminder.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.pfv.advancedreminder.ui.navigation.routes.BottomNavScreens

@Composable
fun BottomNavigationComponent() {

    val navController = rememberNavController()
    var currentRoute by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {

                    BottomNavScreens.values.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.image),
                                    contentDescription = ""
                                )
                            },
                            selected = currentRoute == index,
                            onClick = {
                                navController.navigate(item.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                                currentRoute = index
                            }
                        )
                    }
                }
            )
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
        ){
            MainNavGraph(navController)
        }
    }
}

