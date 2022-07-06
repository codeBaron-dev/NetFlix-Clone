package com.codebaron.filmworld.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material.icons.outlined.DownloadForOffline
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.codebaron.filmworld.models.filmdetails.FilmDetailsData
import com.codebaron.filmworld.models.filmdetails.dummyFilmDetailsData
import com.codebaron.filmworld.models.filmdetails.dummyGenre
import com.codebaron.filmworld.models.filmsdata.Result
import com.codebaron.filmworld.models.filmsdata.trendingResultDummy
import com.codebaron.filmworld.repository.FilmsViewModel
import com.codebaron.filmworld.ui.theme.FilmWorldTheme
import com.codebaron.filmworld.utils.*
import kotlinx.coroutines.delay

@Composable
fun FilmDetailsRequestHandler(movieId: String, filmsViewModel: FilmsViewModel = hiltViewModel()) {

    val localContext = LocalContext.current

    if (isNetworkAvailable(localContext)) {
        val filmDetails by filmsViewModel.getFilmDetails(API_KEY, LANGUAGE_TYPE, movieId)
            .observeAsState()
        val filmCasts by filmsViewModel.getFilmCast(API_KEY, LANGUAGE_TYPE, movieId)
            .observeAsState()
        val similarMovies by filmsViewModel.getSimilarMovies(API_KEY, LANGUAGE_TYPE, movieId)
            .observeAsState()

        LaunchedEffect(Unit) {
            delay(3000)
        }

        FilmDetailsScreen(filmDetails, similarMovies?.ifEmpty { trendingResultDummy })
    }
}

@Composable
fun FilmDetailsScreen(filmDetails: FilmDetailsData?, similarMovies: List<Result>?) {
    Scaffold {
        Column(modifier = Modifier.background(color = Color.Black)) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(550.dp)
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
                                    color = Color.Gray,
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(5.dp)
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                LazyRow {
                                    items(dummyGenre) { genre ->
                                        Text(
                                            text = genre.name.toString(),
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.ExtraLight,
                                            maxLines = 2,
                                            color = Color.Gray,
                                            fontSize = 11.sp,
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
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
            )
            similarMovies?.let { it1 -> SimilarMovies(similarMovies = it1) }
        }
    }
}

@Composable
fun SimilarMovies(similarMovies: List<Result>) {

    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Similar Movies".uppercase(),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            color = Color.White,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        LazyColumn {
            items(similarMovies) { movies ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .width(130.dp)
                                .height(90.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = "$IMAGE_PATH_URL/${movies.poster_path}")
                                    .apply(block = fun ImageRequest.Builder.() {
                                        placeholder(R.drawable.dummyimage)
                                        error(R.drawable.anime)
                                    }).build()
                            ), contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Column(
                            modifier = Modifier
                                .height(90.dp)
                                .width(150.dp)
                        ) {
                            Spacer(modifier = Modifier.size(15.dp))
                            Text(
                                text = movies.title.toString(),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 2,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                            Text(
                                text = MOVIE_WATCH_TIME.random(),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.ExtraLight,
                                maxLines = 1,
                                color = Color.Gray,
                                fontSize = 11.sp
                            )
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.DownloadForOffline,
                                contentDescription = MOVIE_INFO,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    Text(
                        text = movies.overview.toString(),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        maxLines = 4,
                        color = Color.Gray,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsDisplay() {
    FilmWorldTheme {
        FilmDetailsScreen(dummyFilmDetailsData, trendingResultDummy)
    }
}