package com.droidcon.sduicompsemvvm.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.droidcon.sduicompsemvvm.ui.navigation.BottomNavigationBar
import com.droidcon.sduicompsemvvm.ui.navigation.NavigationItem
import com.droidcon.sduicompsemvvm.ui.navigation.NavigationScreens
import com.droidcon.sduicompsemvvm.ui.screens.home.HomeScreen
import com.droidcon.sduicompsemvvm.ui.theme.SduiComposeMvvM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SduiComposeMvvM {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val hideBottomNavRoutes =
                        listOf(NavigationItem.CourseDetail.route) // Replace with your specific routes
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            val currentDestination =
                                navController.currentBackStackEntryAsState().value?.destination?.route
                            if (currentDestination !in hideBottomNavRoutes) {
                                BottomAppBar { BottomNavigationBar(navController = navController) }
                            }
                        },
                    ) { innerPadding ->
                        NavigationScreens(
                            navController = navController,
                            innerPadding = innerPadding
                        )
                    }
                }
            }
        }
    }
}