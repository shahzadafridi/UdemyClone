package com.droidcon.sduicompsemvvm.ui.navigation

import com.droidcon.sduicompsemvvm.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.baseline_home_filled_24, "Home")
    object Cart : NavigationItem("cart", R.drawable.baseline_add_shopping_cart_24, "Cart")
    object CourseDetail : NavigationItem("course_detail", R.drawable.ic_launcher_background, "Course Detail")
    object MyLearning : NavigationItem("my_learning", R.drawable.baseline_library_books_24, "My Learning")
}