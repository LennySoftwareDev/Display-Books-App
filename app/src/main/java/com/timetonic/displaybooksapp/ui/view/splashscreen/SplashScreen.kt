package com.timetonic.displaybooksapp.ui.view.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.timetonic.displaybooksapp.R
import com.timetonic.displaybooksapp.navigation.AppScreens


@Composable
fun SplashScreen(navController: NavHostController, splashScreenViewModel: SplashScreenViewModel) {

    splashScreenViewModel.saveCredentialByDefault()
    splashScreenViewModel.selectApiKey()

    val apiKey = splashScreenViewModel.apiKey.observeAsState().value

    LaunchedEffect(apiKey) {
        if (apiKey != null) {
            navController.popBackStack()
            navController.navigate(AppScreens.MainScreen.route)
        } else {
            splashScreenViewModel.selectApiKey()
        }
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_time_tonic),
            contentDescription = "logo",
            contentScale = ContentScale.Crop
        )
    }
}