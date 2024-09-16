package com.adenikinju.myfirebaseapplication.ui.Screens.Home

import android.content.Context
import android.widget.ImageView.ScaleType
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.adenikinju.myfirebaseapplication.components.BottomNavBar
import com.adenikinju.myfirebaseapplication.components.Navigation
import com.adenikinju.myfirebaseapplication.data.models.MovieModel
import com.google.firebase.auth.FirebaseAuth
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adenikinju.myfirebaseapplication.components.annotedString


@Composable
fun HomeScreen(context: Context, navController: NavHostController) {

    val viewmodel: HomeViewModel = hiltViewModel()
    val movies = viewmodel.movies.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues()),
        backgroundColor = Color(21, 20, 20),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        AnnotatedString(text = "Movie Lists"),
                        Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(900),
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }, backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.padding(top = 40.dp)
            )
        },
        bottomBar = { BottomNavBar(navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            MovieScreen(modifier = Modifier, movies, context)
        }

    }

}

@Composable
private fun MovieScreen(
    modifier: Modifier,
    list: List<MovieModel>,
    context: Context
) {
    var imageUrl by remember { mutableStateOf("/waPt1Dv5kWhbNna5rTv2NGfeb7O.jpg") }
    var title by remember { mutableStateOf("Deadpool & Wolverine") }
    var rating by remember { mutableStateOf("2680.931") }

    Column {
        MovieTheme(
            modifier,
            imageUrl = imageUrl,
            title = title,
            rating = rating,
            context
        )
        Column {
            Spacer(modifier = Modifier.padding(vertical = 15.dp))
            Text(
                AnnotatedString(text = "Movies"),
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp)
            )
            LazyRow(
                modifier = Modifier.padding(20.dp)
            ) {
                items(list.size) { movie ->
                    val currentItem = list[movie]
                    MovieItem(
                        originalLangauge = currentItem.original_language,
                        title = currentItem.title,
                        imageUrl = currentItem.poster_path,
                        rating = currentItem.popularity.toString(),
                        context,
                        onclick = {imageUrl = currentItem.poster_path}
                    )
                }
            }
        }

    }
}

@Composable
private fun MovieItem(
    originalLangauge: String,
    title: String,
    imageUrl: String,
    rating: String,
    context: Context,
    onclick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(end = 10.dp)
            .clickable { onclick() }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("https://image.tmdb.org/t/p/original" + imageUrl)
                .build(),
            contentDescription = "movie image",
            modifier = Modifier
                .fillMaxSize(1f)
                .clip(RoundedCornerShape(20.dp))
        )
        // Text(text = title)
    }

}

@Composable
private fun MovieTheme(
    modifier: Modifier,
    imageUrl: String,
    title: String,
    rating: String,
    context: Context,
) {
    Row(
        modifier
            .height(500.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("https://image.tmdb.org/t/p/original" + imageUrl)
                .build(),
            contentDescription = "movie image",
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxSize(),
            contentScale = ContentScale.Crop,

            )
        Text(text = title)
    }

}