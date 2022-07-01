package com.codebaron.filmworld.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebaron.filmworld.R
import com.codebaron.filmworld.navigation.Destinations
import com.codebaron.filmworld.ui.theme.FilmWorldTheme
import com.codebaron.filmworld.utils.ADD_PROFILE
import com.codebaron.filmworld.utils.KIDS
import com.codebaron.filmworld.utils.NAME
import com.codebaron.filmworld.utils.VIEWER

@Composable
fun OptionScreen(navigationController: NavHostController) {
    Scaffold(
        topBar = { HeaderView() }
    ) {
        Column {
            BodyView(navigationController)
        }
    }
}

@Composable
fun HeaderView() {
    Column {
        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .background(color = Color.Black),
            contentAlignment = Alignment.TopCenter
        ) {}

        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(color = Color.Black),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(150.dp),
                    painter = painterResource(id = R.drawable.net),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun BodyView(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = VIEWER,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .clickable {
                                navigationController.navigate(Destinations.MOVIES_HOME_SCREEN.name)
                            },
                        painter = painterResource(id = R.drawable.anime),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = NAME,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.size(30.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(id = R.drawable.dummyimage),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = NAME,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(id = R.drawable.anime),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = KIDS,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.size(30.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .width(90.dp)
                                .height(50.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            imageVector = Icons.Filled.AddCircle,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Text(
                        text = ADD_PROFILE,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UiDisplay() {
    FilmWorldTheme() {
        OptionScreen(navigationController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun BodyViewDisplay() {
    FilmWorldTheme {
        BodyView(navigationController = rememberNavController())
    }
}