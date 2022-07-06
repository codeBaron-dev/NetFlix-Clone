@file:OptIn(ExperimentalMaterialApi::class)

package com.codebaron.filmworld.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.PlayCircle
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.codebaron.filmworld.R
import com.codebaron.filmworld.models.filmsdata.Result
import com.codebaron.filmworld.models.filmsdata.trendingResultDummy
import com.codebaron.filmworld.navigation.Destinations.MOVIE_DETAILS_SCREEN
import com.codebaron.filmworld.repository.FilmsViewModel
import com.codebaron.filmworld.ui.theme.FilmWorldTheme
import com.codebaron.filmworld.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FilmsRequestHandler(
    navigationController: NavHostController,
    filmsViewModel: FilmsViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current

    if (isNetworkAvailable(localContext)) {
        // observe films state
        val popularFilms by filmsViewModel.getPopularFilms(API_KEY, LANGUAGE_TYPE, PAGE_PARAM)
            .observeAsState(emptyList())
        val trendingFilms by filmsViewModel.getTrendingFilms(API_KEY).observeAsState(emptyList())
        val topRatedFilms by filmsViewModel.getTopRatedFilms(API_KEY, LANGUAGE_TYPE, PAGE_PARAM)
            .observeAsState(emptyList())
        LaunchedEffect(Unit) {
            delay(3000)
        }
        HomeScreen(popularFilms, trendingFilms, topRatedFilms, navigationController)
    } else {
        HomeScreen(
            trendingResultDummy,
            trendingResultDummy,
            trendingResultDummy,
            navigationController
        )
        CustomMaterialDialog(
            INTERNET_ERROR_TITLE,
            INTERNET_ERROR_MESSAGE,
            POSITIVE_BUTTON_TEXT,
            NEGATIVE_BUTTON_TEXT
        )
    }
}

@Composable
fun HomeScreen(
    popularFilmsList: List<Result>,
    trendingFilmsList: List<Result>,
    topRatedFilmsList: List<Result>,
    navigationController: NavHostController
) {
    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black)
        ) {
            Column {
                val popularFilmContent = popularFilmsList.ifEmpty { trendingResultDummy }
                val trendingFilmContent = trendingFilmsList.ifEmpty { trendingResultDummy }
                val topRatedFilmContent = topRatedFilmsList.ifEmpty { trendingResultDummy }

                MovieHeaderView(
                    popularFilmsList = popularFilmContent,
                    trendingFilmsList = trendingFilmContent,
                    topRatedFilmsList = topRatedFilmContent,
                    navigationController = navigationController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderBar() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        startY = size.height / 19,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                },
            contentAlignment = Alignment.TopCenter
        ) {}
        Column {
            Spacer(modifier = Modifier.size(35.dp))
            Row {
                Box(
                    contentAlignment = Alignment.TopStart
                ) {
                    Image(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.netflixicon),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = SEARCH,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Image(
                        modifier = Modifier
                            .height(35.dp)
                            .width(40.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(id = R.drawable.anime),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = TV_SHOWS,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    maxLines = 1,
                    color = Color.White,
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = MOVIES,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    maxLines = 1,
                    color = Color.White,
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = HOT_NEW,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    maxLines = 1,
                    color = Color.White,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}

@Composable
fun MovieHeaderView(
    popularFilmsList: List<Result>,
    trendingFilmsList: List<Result>,
    topRatedFilmsList: List<Result>,
    navigationController: NavHostController
) {

    val getRandomVideoObject = popularFilmsList.random()
    val localContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(color = Color.Black)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(490.dp)
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
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
                            .data(data = "$IMAGE_PATH_URL/${getRandomVideoObject.poster_path}")
                            .apply(block = fun ImageRequest.Builder.() {
                                placeholder(R.drawable.dummyimage)
                                error(R.drawable.anime)
                            }).build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            HeaderBar()
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            getRandomVideoObject.original_title?.let {
                                Text(
                                    text = it,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    color = Color.White
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
                                    imageVector = Icons.Default.Add,
                                    contentDescription = ADD_IMAGE_DESCRIPTION,
                                    tint = Color.White
                                )
                                Text(
                                    text = MY_LIST,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Justify,
                                    maxLines = 1,
                                    color = Color.White
                                )
                            }
                            Card(
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(60.dp)
                                    .padding(15.dp)
                                    .clickable {
                                        Toast
                                            .makeText(
                                                localContext,
                                                INVALID_ACTION,
                                                Toast.LENGTH_LONG
                                            )
                                            .show()
                                    }
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
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable {
                                    val encodedUrl =
                                        URLEncoder.encode(
                                            getRandomVideoObject.id.toString(),
                                            StandardCharsets.UTF_8.toString()
                                        )
                                    if (isNetworkAvailable(localContext)) {
                                        if (encodedUrl.isNotEmpty()) {
                                            navigationController.navigate("${MOVIE_DETAILS_SCREEN.name}/$encodedUrl")
                                        } else {
                                            Toast.makeText(
                                                localContext,
                                                UNKNOWN_ERROR,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            localContext,
                                            INTERNET_ERROR_TITLE,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }) {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = MOVIE_INFO,
                                    tint = Color.White
                                )
                                Text(
                                    text = INFO,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Justify,
                                    maxLines = 1,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        PopularMoviesList(popularFilmsList, navigationController)

        Spacer(modifier = Modifier.size(1.dp))
        TrendingNowList(trendingFilmsList, navigationController)

        Spacer(modifier = Modifier.size(1.dp))
        TopRated(topRatedFilmsList, navigationController)
    }
}

//currently not in use
@Composable
fun ContinueWatchingList(
    videos: List<Result>,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = CONTINUE_WATCHING,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            maxLines = 1,
            color = Color.White
        )
        LazyRow {
            items(videos) { content ->
                Card(modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable {}
                ) {
                    Column {
                        BoxWithConstraints(
                            modifier = Modifier
                                .width(120.dp)
                                .height(200.dp)
                        ) {
                            Box {
                                Image(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(),
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = "$IMAGE_PATH_URL/${content.poster_path}")
                                            .apply(block = fun ImageRequest.Builder.() {
                                                placeholder(IMAGES.random())
                                                error(IMAGES.random())
                                            }).build()
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.PlayCircle,
                                    contentDescription = PLAY,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clickable { }
                                )
                            }
                            Column(
                                modifier = Modifier.align(Alignment.BottomCenter)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.BottomCenter
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "${SEASONS.random()}:${EPISODES.random()}",
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Justify,
                                            maxLines = 1,
                                            color = Color.White,
                                            modifier = Modifier.padding(3.dp)
                                        )
                                        LinearProgressIndicator(
                                            progress = PROGRESS_LEVELS.random(),
                                            color = Color.Red,
                                            modifier = Modifier.width(120.dp)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .background(color = Color.DarkGray)
                                        .clickable { }
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Info,
                                            contentDescription = INFO,
                                            tint = Color.White,
                                            modifier = Modifier.clickable {
                                                coroutineScope.launch {
                                                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                                        bottomSheetScaffoldState.bottomSheetState.expand()
                                                    } else {
                                                        bottomSheetScaffoldState.bottomSheetState.collapse()
                                                    }
                                                }
                                            }
                                        )
                                        Spacer(modifier = Modifier.size(45.dp))
                                        Icon(
                                            imageVector = Icons.Outlined.MoreVert,
                                            contentDescription = MORE_OPTION,
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
}

@Composable
fun PopularMoviesList(videos: List<Result>, navigationController: NavHostController) {

    val localContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = POPULAR_NOW,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            maxLines = 1,
            color = Color.White
        )
        LazyRow {
            items(videos) { content ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clickable {
                            val encodedUrl =
                                URLEncoder.encode(
                                    content.id.toString(),
                                    StandardCharsets.UTF_8.toString()
                                )
                            if (isNetworkAvailable(localContext)) {
                                if (encodedUrl.isNotEmpty()) {
                                    navigationController.navigate("${MOVIE_DETAILS_SCREEN.name}/$encodedUrl")
                                } else {
                                    Toast
                                        .makeText(localContext, UNKNOWN_ERROR, Toast.LENGTH_LONG)
                                        .show()
                                }
                            } else {
                                Toast
                                    .makeText(
                                        localContext,
                                        INTERNET_ERROR_TITLE,
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }
                        }
                ) {
                    Column {
                        BoxWithConstraints(
                            modifier = Modifier
                                .width(120.dp)
                                .height(170.dp)
                        ) {
                            Box {
                                Image(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(),
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = "$IMAGE_PATH_URL/${content.poster_path}")
                                            .apply(block = fun ImageRequest.Builder.() {
                                                placeholder(IMAGES.random())
                                                error(IMAGES.random())
                                            }).build()
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TrendingNowList(videos: List<Result>, navigationController: NavHostController) {

    val localContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = TRENDING_NOW,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            maxLines = 1,
            color = Color.White
        )
        LazyRow {
            items(videos) { content ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clickable {
                            val encodedUrl =
                                URLEncoder.encode(
                                    content.id.toString(),
                                    StandardCharsets.UTF_8.toString()
                                )
                            if (isNetworkAvailable(localContext)) {
                                if (encodedUrl.isNotEmpty()) {
                                    navigationController.navigate("${MOVIE_DETAILS_SCREEN.name}/$encodedUrl")
                                } else {
                                    Toast
                                        .makeText(localContext, UNKNOWN_ERROR, Toast.LENGTH_LONG)
                                        .show()
                                }
                            } else {
                                Toast
                                    .makeText(
                                        localContext,
                                        INTERNET_ERROR_TITLE,
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }
                        }
                ) {
                    Column {
                        BoxWithConstraints(
                            modifier = Modifier
                                .width(120.dp)
                                .height(170.dp)
                        ) {
                            Box {
                                Image(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(),
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = "$IMAGE_PATH_URL/${content.poster_path}")
                                            .apply(block = fun ImageRequest.Builder.() {
                                                placeholder(IMAGES.random())
                                                error(IMAGES.random())
                                            }).build()
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopRated(videos: List<Result>, navigationController: NavHostController) {

    val localContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = TOP_RATED,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            maxLines = 1,
            color = Color.White
        )
        LazyRow {
            items(videos) { content ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clickable {
                            val encodedUrl =
                                URLEncoder.encode(
                                    content.id.toString(),
                                    StandardCharsets.UTF_8.toString()
                                )
                            if (isNetworkAvailable(localContext)) {
                                if (encodedUrl.isNotEmpty()) {
                                    navigationController.navigate("${MOVIE_DETAILS_SCREEN.name}/$encodedUrl")
                                } else {
                                    Toast
                                        .makeText(localContext, UNKNOWN_ERROR, Toast.LENGTH_LONG)
                                        .show()
                                }
                            } else {
                                Toast
                                    .makeText(
                                        localContext,
                                        INTERNET_ERROR_TITLE,
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }
                        }
                ) {
                    Column {
                        BoxWithConstraints(
                            modifier = Modifier
                                .width(120.dp)
                                .height(170.dp)
                        ) {
                            Box {
                                Image(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(),
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = "$IMAGE_PATH_URL/${content.poster_path}")
                                            .apply(block = fun ImageRequest.Builder.() {
                                                placeholder(IMAGES.random())
                                                error(IMAGES.random())
                                            }).build()
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
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
fun MoviePreview() {
    FilmWorldTheme {
        MovieHeaderView(
            trendingResultDummy,
            trendingResultDummy,
            trendingResultDummy,
            rememberNavController()
        )
    }
}