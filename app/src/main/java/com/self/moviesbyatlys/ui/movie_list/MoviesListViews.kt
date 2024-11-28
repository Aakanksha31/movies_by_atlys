package com.self.moviesbyatlys.ui.movie_list

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.self.moviesbyatlys.R
import com.self.moviesbyatlys.models.MovieItem
import com.self.moviesbyatlys.models.OneOffEvent
import com.self.moviesbyatlys.ui.common.FullScreenEmptyView
import com.self.moviesbyatlys.ui.common.FullScreenLoader
import com.self.moviesbyatlys.ui.theme.ContainerColour
import com.self.moviesbyatlys.ui.theme.IconColour
import com.self.moviesbyatlys.ui.theme.LabelColour
import com.self.moviesbyatlys.ui.viewmodel.MoviesViewModel
import com.self.moviesbyatlys.utils.ObserveAsOneOffEvents
import com.self.moviesbyatlys.utils.showToast


@Composable
fun MovieListScreen(viewModel: MoviesViewModel, onItemClicked: (MovieItem) -> Unit) {

    //observing api errors
    val context = LocalContext.current
    ObserveAsOneOffEvents(viewModel.oneOffEvent) {
        when (it) {
            is OneOffEvent.ShowError -> {
                context.showToast(it.message)
            }
        }
    }

    //observing ui state
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    if (uiState.loading) {
        FullScreenLoader(loadingText = stringResource(R.string.loading_movies))
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            SearchView(
                searchQuery = uiState.query,
                onQueryChanged = { value ->
                    viewModel.onSearchQueryChanged(value)
                })

            Spacer(modifier = Modifier.height(30.dp))

            if (uiState.movies.isEmpty()) {
                FullScreenEmptyView()
            } else {
                MoviesGrid(uiState.movies, onItemClicked)
            }
        }
    }
}

@Composable
fun SearchView(
    searchQuery: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ContainerColour,
            unfocusedBorderColor = ContainerColour
        ).copy(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLeadingIconColor = IconColour,
            unfocusedLeadingIconColor = IconColour,
            focusedLabelColor = LabelColour,
            unfocusedLabelColor = LabelColour
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
        value = searchQuery,
        onValueChange = {
            onQueryChanged(it)
        },
        leadingIcon = {
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        },
        maxLines = 1,
        label = { Text(text = stringResource(R.string.search_movies)) })
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
        items(list.size) { index ->
            MovieCard(
                item = list[index],
                onClick = { onItemClicked(list[index]) })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(item: MovieItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                model = item.poster,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                loading = placeholder(painterResource(id = R.drawable.placeholder)),
                failure = placeholder(painterResource(id = R.drawable.image_error))
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

@Preview
@Composable
fun SearchPreview() {
    SearchView(
        modifier = Modifier.padding(horizontal = 16.dp),
        searchQuery = "",
        onQueryChanged = { })
}