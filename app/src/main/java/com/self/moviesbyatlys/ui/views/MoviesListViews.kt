package com.self.moviesbyatlys.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.self.moviesbyatlys.MovieItem
import com.self.moviesbyatlys.R
import com.self.moviesbyatlys.ui.MoviesViewModel


@Composable
fun MovieListScreen(viewModel: MoviesViewModel, onItemClicked: (MovieItem) -> Unit) {
    val list = listOf(
        MovieItem(
            title = "Movie 1",
            description = "hvfdjkdnvkjdfn",
            photo = R.drawable.placeholder
        ),
        MovieItem(
            title = "Movie 2",
            description = "hvfdjkdnvkjdfn",
            photo = R.drawable.placeholder
        ),
        MovieItem(
            title = "Movie 3",
            description = "hvfdjkdnvkjdfn",
            photo = R.drawable.placeholder
        ),
    )
    MoviesGrid(list, onItemClicked)
}

@Composable
fun MoviesGrid(list: List<MovieItem>, onItemClicked: (MovieItem) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(list.size) { index -> // Replace with actual movie list size
            MovieCard(
                item = list[index],
                onClick = { onItemClicked(list[index])/*navController.navigate(route = Screen.MovieDetailScreen.route + "?id=${id.value}")*/ })
        }
    }
}

@Composable
fun MovieCard(item: MovieItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = item.photo),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}