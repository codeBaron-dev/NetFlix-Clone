package com.codebaron.filmworld.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.codebaron.filmworld.R
import com.codebaron.filmworld.models.filmdetails.dummyFilmDetailsData
import com.codebaron.filmworld.models.filmdetails.dummyGenre
import com.codebaron.filmworld.utils.ADD_IMAGE_DESCRIPTION
import com.codebaron.filmworld.utils.MOVIE_INFO
import com.codebaron.filmworld.utils.PLAY
import com.codebaron.filmworld.utils.PLAY_BUTTON_DESCRIPTION

@Composable
fun FilmDetailsRequestHandler() {
    Text(text = "Games Screen")
}

@Preview(showBackground = true)
@Composable
fun FilmDetailsScreen() {
    val dummyData = dummyFilmDetailsData
    Scaffold {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Gray, Color.Black),
                            startY = size.height / 90,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    },
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = "")
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.dummyimage)
                            error(R.drawable.anime)
                        }).build()
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(color = Color.Black)
            ) {
                Box(modifier = Modifier.background(Color.Black)) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = size.height / 20,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient, blendMode = BlendMode.Multiply)
                                }
                            },
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = "")
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(R.drawable.dummyimage)
                                    error(R.drawable.anime)
                                }).build()
                        ), contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = dummyData.title,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                Row {
                                    Text(
                                        text = "Tagline: ",
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )
                                    Text(
                                        text = dummyData.tagline,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )
                                }
                                Spacer(modifier = Modifier.size(5.dp))
                                Row {
                                    /*Text(
                                        text = "Overview: ",
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )*/
                                    Text(
                                        text = dummyData.overview,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 4,
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )
                                }
                                Spacer(modifier = Modifier.size(5.dp))
                                Row {
                                    Text(
                                        text = "Genre:",
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )
                                    Spacer(modifier = Modifier.size(5.dp))
                                    LazyRow {
                                        items(dummyGenre) { genre ->
                                            Text(
                                                text = genre.name,
                                                fontFamily = FontFamily.Monospace,
                                                fontWeight = FontWeight.Bold,
                                                maxLines = 2,
                                                color = Color.White,
                                                fontSize = 11.sp
                                            )
                                        }
                                    }
                                }
                                Row {
                                    Text(
                                        text = "Status: ",
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )
                                    Text(
                                        text = "${dummyData.status} on ${dummyData.release_date}",
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = ADD_IMAGE_DESCRIPTION,
                                        tint = Color.White
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Default.List,
                                        contentDescription = ADD_IMAGE_DESCRIPTION,
                                        tint = Color.White
                                    )
                                }
                                Card(
                                    modifier = Modifier
                                        .width(110.dp)
                                        .height(60.dp)
                                        .padding(15.dp)
                                        .clickable { }
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = PLAY_BUTTON_DESCRIPTION,
                                            tint = Color.Black
                                        )
                                        Text(
                                            text = PLAY,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Justify,
                                            maxLines = 1,
                                            color = Color.Black
                                        )
                                    }
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Outlined.Bookmark,
                                        contentDescription = MOVIE_INFO,
                                        tint = Color.White
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Outlined.StarRate,
                                        contentDescription = MOVIE_INFO,
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}