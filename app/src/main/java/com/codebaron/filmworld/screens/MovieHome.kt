package com.codebaron.filmworld.screens

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
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.codebaron.filmworld.R
import com.codebaron.filmworld.models.Result
import com.codebaron.filmworld.models.trendingResultDummy
import com.codebaron.filmworld.utils.*

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black)
        ) {
            Column {
                MovieHeaderView(properties = trendingResultDummy)
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
                        colors = listOf(Color.Gray, Color.Transparent),
                        startY = size.height / 15,
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
fun MovieHeaderView(properties: List<Result>) {

    val getRandomVideoObject = properties.random()

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
                            .data(data = "$IMAGE_PATH_URL/${getRandomVideoObject.poster_path.random()}")
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
                            Text(
                                text = getRandomVideoObject.original_title,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Justify,
                                maxLines = 1,
                                color = Color.White
                            )
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
        ContinueWatchingList(trendingResultDummy)
        Spacer(modifier = Modifier.size(1.dp))
        TrendingNowList(trendingResultDummy)
        Spacer(modifier = Modifier.size(1.dp))
        TopRated(trendingResultDummy)
    }
}

@Composable
fun ContinueWatchingList(videos: List<Result>) {
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
                                    modifier = Modifier.size(70.dp)
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
                                            tint = Color.White
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
fun TrendingNowList(videos: List<Result>) {
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
                        .clickable {}
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
fun TopRated(videos: List<Result>) {
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
                        .clickable {}
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