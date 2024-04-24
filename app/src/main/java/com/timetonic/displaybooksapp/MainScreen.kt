package com.timetonic.displaybooksapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.timetonic.displaybooksapp.ui.view.login.LoginScreen
import com.timetonic.displaybooksapp.ui.view.splashscreen.SplashScreenViewModel
import com.timetonic.displaybooksapp.ui.view.login.LoginViewModel

@Composable
fun MainScreen(
    loginViewModel: LoginViewModel,
    splashScreenViewModel: SplashScreenViewModel,
    navController: NavController
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginScreen(loginViewModel,splashScreenViewModel,navController)
    }
}