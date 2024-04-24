package com.timetonic.displaybooksapp.ui.view.descriptionbooks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.timetonic.displaybooksapp.utils.ConstantValues
import com.timetonic.displaybooksapp.R
import com.timetonic.displaybooksapp.data.api.modelresponse.getbooks.GetAllBooksResponseDto
import com.timetonic.displaybooksapp.navigation.AppScreens
import com.timetonic.displaybooksapp.ui.view.login.LoginViewModel


@Composable
fun DescriptionBookScreen(
    loginViewModel: LoginViewModel,
    descriptionBookViewModel: DescriptionBookViewModel,
    navController: NavController
) {

    val isLoadingBooks: Boolean by descriptionBookViewModel.isLoadingBooks.observeAsState(initial = false)

    val oAuthKey =
        loginViewModel.dataCreateOAuthKey.value?.getOrNull()?.oAuthKey.toString()

    descriptionBookViewModel.getOAuthKey(oAuthKey)

    val sessKey =
        descriptionBookViewModel.dataCreateSessKey.value?.getOrNull()?.sessKey.toString()

    descriptionBookViewModel.getSessKey(sessKey)

    descriptionBookViewModel.saveSessKey()

    val isSessKeySuccess: Boolean by descriptionBookViewModel.isSessKeySuccess.observeAsState(
        initial = false
    )

    LaunchedEffect(isLoadingBooks) {
        descriptionBookViewModel.onCreateSessKey(
            ou = "androiddeveloper",
            oAuthKey = descriptionBookViewModel.oAuthKey.value.toString(),
            restriction = ""
        )
    }

    LaunchedEffect(isSessKeySuccess) {
        descriptionBookViewModel.getAllBooks(
            ou = "androiddeveloper",
            uc = "androiddeveloper",
            sessKey = descriptionBookViewModel.sessKey.value.toString()
        )
    }

    ItemBook(descriptionBookViewModel, navController)
}

@Composable
fun ItemBook(descriptionBookViewModel: DescriptionBookViewModel, navController: NavController) {
    val dataGetAllBooks by descriptionBookViewModel.dataGetAllBooks.observeAsState()

    if (dataGetAllBooks?.isSuccess == true) {
        LazyColumn(verticalArrangement = Arrangement.SpaceBetween) {
            dataGetAllBooks?.getOrNull()?.allBooks?.nbBooks?.let {
                item {
                    DescriptionBook(getAllBooksResponseDto = dataGetAllBooks)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),  //important
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .size(100.dp),
                painter = painterResource(id = R.drawable.book),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Loading library"
            )

        }
        if (descriptionBookViewModel.isLoadingBooks.value == false)
            Button(
                onClick = {
                    navController.navigate(AppScreens.MainScreen.route)
                })
            {
                Text(text = "Error")
                Text(text = "Retry")
            }
    }
}

@Composable
fun DescriptionBook(getAllBooksResponseDto: Result<GetAllBooksResponseDto>?) {

    val mapDataBooks: MutableMap<String, String> = mutableMapOf()
    var count = 0
    while (count != getAllBooksResponseDto?.getOrNull()?.allBooks?.books!!.size) {
        if (count == getAllBooksResponseDto.getOrNull()?.allBooks?.books!!.size) {
            break
        }
        getAllBooksResponseDto.getOrNull()?.allBooks?.books?.forEach {
            mapDataBooks[it.ownerPrefs.title] = it.ownerPrefs.oCoverImg
        }

        mapDataBooks.forEach {
            Text(
                text = it.key,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                shape = MaterialTheme.shapes.small,
                border = BorderStroke(2.dp, Color.Red),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.blue),
                    contentColor = colorResource(id = R.color.white),
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    SubcomposeAsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = ConstantValues.BASE_URL_IMAGES
                            .plus(it.value),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    ) {
                        val painterState = painter.state
                        if (painterState is AsyncImagePainter.State.Loading
                            ||
                            painterState is AsyncImagePainter.State.Error
                        ) {
                            CircularProgressIndicator()
                        } else {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            }
            count++
        }
    }
}
