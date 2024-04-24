package com.timetonic.displaybooksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.timetonic.displaybooksapp.ui.view.splashscreen.SplashScreen
import com.timetonic.displaybooksapp.navigation.AppScreens
import com.timetonic.displaybooksapp.ui.theme.DisplayBooksAppTheme
import com.timetonic.displaybooksapp.ui.view.descriptionbooks.DescriptionBookScreen
import com.timetonic.displaybooksapp.ui.view.descriptionbooks.DescriptionBookViewModel
import com.timetonic.displaybooksapp.ui.view.login.LoginViewModel
import com.timetonic.displaybooksapp.ui.view.splashscreen.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()
    private val descriptionBookViewModel: DescriptionBookViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisplayBooksAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AppScreens.SplashScreen.route,
                        builder = {
                            composable(AppScreens.SplashScreen.route) {
                                SplashScreen(navController, splashScreenViewModel)
                            }
                            composable(AppScreens.MainScreen.route) {
                                MainScreen(
                                    loginViewModel,
                                    splashScreenViewModel,
                                    navController
                                )
                            }
                            composable(AppScreens.DescriptionBookScreen.route) {
                                DescriptionBookScreen(
                                    loginViewModel,
                                    descriptionBookViewModel,
                                    navController
                                )
                            }
                        })
                }
            }
        }
    }
}