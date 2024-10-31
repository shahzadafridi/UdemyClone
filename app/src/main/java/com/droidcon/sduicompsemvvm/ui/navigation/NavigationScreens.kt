package com.droidcon.sduicompsemvvm.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.ui.screens.Cart.CartScreen
import com.droidcon.sduicompsemvvm.ui.screens.course.CourseDetailScreen
import com.droidcon.sduicompsemvvm.ui.screens.home.HomeScreen
import com.droidcon.sduicompsemvvm.ui.screens.learning.MyLearningScreen

@Composable
fun NavigationScreens(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                innerPadding = innerPadding,
                navigateToCourseDetail = { course ->
                    // Pass the User object as a Parcelable
                    navController.currentBackStackEntry?.savedStateHandle?.set("course", course)
                    navController.navigate(NavigationItem.CourseDetail.route)
                }
            )
        }
        composable(NavigationItem.Cart.route) {
            CartScreen(innerPadding = innerPadding)
        }
        composable(NavigationItem.MyLearning.route) {
            MyLearningScreen(innerPadding = innerPadding)
        }
        composable(NavigationItem.CourseDetail.route) {
            // Retrieve the User object from the saved state handle
            val course = navController.previousBackStackEntry?.savedStateHandle?.get<Course>("course")
            if (course != null) {
                CourseDetailScreen(data = course, navController = navController)
            }
        }
    }
}