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
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.codebaron.filmworld.R
import com.codebaron.filmworld.models.filmcredits.FilmCasts
import com.codebaron.filmworld.models.filmcredits.dummyFilmCasts
import com.codebaron.filmworld.models.filmdetails.FilmDetailsData
import com.codebaron.filmworld.models.filmdetails.dummyFilmDetailsData
import com.codebaron.filmworld.models.filmdetails.dummyGenre
import com.codebaron.filmworld.repository.FilmsViewModel
import com.codebaron.filmworld.ui.theme.FilmWorldTheme
import com.codebaron.filmworld.utils.*

@Composable
fun FilmDetailsRequestHandler(movieId: String, filmsViewModel: FilmsViewModel = hiltViewModel()) {
    val localContext = LocalContext.current
    if (isNetworkAvailable(localContext)) {
        val filmDetails by filmsViewModel.getFilmDetails(API_KEY, "en-US", movieId).observeAsState()
        val filmCasts by filmsViewModel.getFilmCast(API_KEY, "en-US", movieId).observeAsState()
        FilmDetailsScreen(filmDetails, filmCasts)
    }
}

@Composable
fun FilmDetailsScreen(filmDetails: FilmDetailsData?, filmCasts: FilmCasts?) {
    val movieCrew = remember { mutableListOf(filmCasts?.crew)}
    Scaffold {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black)
        ) {
            Column {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(color = Color.Black)
                ) {
                    Box(modifier = Modifier.background(Color.Black)) {
                        Column {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .drawWithCache {
                                        val gradient = Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, Color.Black),
                                            startY = size.height / 30,
                                            endY = size.height
                                        )
                                        onDrawWithContent {
                                            drawContent()
                                            drawRect(gradient, blendMode = BlendMode.Multiply)
                                        }
                                    },
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(data = "$IMAGE_PATH_URL/${filmDetails?.backdrop_path}")
                                        .apply(block = fun ImageRequest.Builder.() {
                                            placeholder(R.drawable.dummyimage)
                                            error(R.drawable.anime)
                                        }).build()
                                ), contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                            Column(modifier = Modifier.padding(5.dp)) {
                                Text(
                                    text = filmDetails?.title.toString(),
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    color = Color.White,
                                    fontSize = 25.sp
                                )
                                Text(
                                    text = "${filmDetails?.status} on ${filmDetails?.release_date}",
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.ExtraLight,
                                    maxLines = 2,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
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
                                        Spacer(modifier = Modifier.size(5.dp))
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
                                Spacer(modifier = Modifier.size(5.dp))
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .clickable { },
                                    backgroundColor = Color.DarkGray
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.DownloadForOffline,
                                            contentDescription = PLAY_BUTTON_DESCRIPTION,
                                            tint = Color.White
                                        )
                                        Spacer(modifier = Modifier.size(5.dp))
                                        Text(
                                            text = "Download",
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Justify,
                                            maxLines = 1,
                                            color = Color.White
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.size(5.dp))
                                Text(
                                    text = filmDetails?.overview.toString(),
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Light,
                                    maxLines = 4,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                Row {
                                    Text(
                                        text = "Genre:",
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.ExtraLight,
                                        maxLines = 2,
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Spacer(modifier = Modifier.size(5.dp))
                                    LazyRow {
                                        items(dummyGenre) { genre ->
                                            Text(
                                                text = genre.name,
                                                fontFamily = FontFamily.Monospace,
                                                fontWeight = FontWeight.ExtraLight,
                                                maxLines = 2,
                                                color = Color.White,
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.size(5.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.width(50.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Favorite,
                                            contentDescription = ADD_IMAGE_DESCRIPTION,
                                            tint = Color.White
                                        )
                                        Text(
                                            text = "Mark As Liked",
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.ExtraLight,
                                            maxLines = 2,
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.width(50.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.List,
                                            contentDescription = ADD_IMAGE_DESCRIPTION,
                                            tint = Color.White
                                        )
                                        Text(
                                            text = "Add To List",
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.ExtraLight,
                                            maxLines = 2,
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.width(50.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Bookmark,
                                            contentDescription = MOVIE_INFO,
                                            tint = Color.White
                                        )
                                        Text(
                                            text = "Add To Watchlist",
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.ExtraLight,
                                            maxLines = 2,
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.width(50.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.StarRate,
                                            contentDescription = MOVIE_INFO,
                                            tint = Color.White
                                        )
                                        Text(
                                            text = "Rate Movie",
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.ExtraLight,
                                            maxLines = 2,
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            textAlign = TextAlign.Center
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
}

@Preview(showBackground = true)
@Composable
fun DetailsDisplay() {
    FilmWorldTheme {
        FilmDetailsScreen(dummyFilmDetailsData, dummyFilmCasts)
    }
}