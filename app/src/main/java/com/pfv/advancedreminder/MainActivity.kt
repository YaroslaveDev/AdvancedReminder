package com.pfv.advancedreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.pfv.advancedreminder.permissions.requestNotificationPermission
import com.pfv.advancedreminder.ui.navigation.RootNavGraph
import com.pfv.advancedreminder.ui.theme.AdvancedReminderTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            requestNotificationPermission()

            AdvancedReminderTheme(
                dynamicColor = false
            ) {

                enableEdgeToEdge()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    RootNavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}
