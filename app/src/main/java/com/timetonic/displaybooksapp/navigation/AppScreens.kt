package com.timetonic.displaybooksapp.navigation

sealed class AppScreens(val route: String){
    object SplashScreen: AppScreens("splash_screen")
    object MainScreen: AppScreens("main_screen")
    object DescriptionBookScreen: AppScreens("description_book_screen")
}