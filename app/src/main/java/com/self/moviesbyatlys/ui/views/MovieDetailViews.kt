package com.self.moviesbyatlys.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.self.moviesbyatlys.MovieDetail
import com.self.moviesbyatlys.R
import com.self.moviesbyatlys.ui.theme.IconColour


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDetailScreen(selectedMovie: MovieDetail, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        // Back Button
        IconButton(
            onClick = {
                onBackClick()
            }
        ) {
            androidx.compose.material3.Icon(
                tint = IconColour,
                painter = painterResource(id = R.drawable.btn_back),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Movie Poster
        Box(
            modifier = Modifier
                .height(360.dp)
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                model = selectedMovie.moviePoster,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                loading = placeholder(painterResource(id = R.drawable.placeholder)),
                failure = placeholder(painterResource(id = R.drawable.placeholder))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Movie Title
        Text(text = selectedMovie.movieTitle, fontSize = 24.sp, color = Color.Black)

        // Movie Description
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = selectedMovie.movieDescription,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}