package com.codebaron.filmworld.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebaron.filmworld.R
import com.codebaron.filmworld.navigation.Destinations
import com.codebaron.filmworld.ui.theme.FilmWorldTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigationController: NavHostController) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .width(300.dp),
                painter = painterResource(id = R.drawable.net),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
        LaunchedEffect(Unit) {
            delay(3000)
            navigationController.navigate(Destinations.OPTION_SCREEN.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenDisplay() {
    FilmWorldTheme {
        SplashScreen(navigationController = rememberNavController())
    }
}