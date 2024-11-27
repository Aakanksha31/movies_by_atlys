package com.self.moviesbyatlys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.self.moviesbyatlys.ui.views.MovieDetailScreen
import com.self.moviesbyatlys.ui.theme.MoviesByAtlysTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.self.moviesbyatlys.ui.views.MovieListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MoviesByAtlysTheme {
                MovieApp()
            }
        }
    }
}

@Composable
fun MovieApp(
    viewModel: MoviesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MovieAppScreen.MoviesList.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MovieAppScreen.MoviesList.name) {
                MovieListScreen(viewModel = viewModel, onItemClicked = { movieItem ->
                    viewModel.setSelectedItem(movieItem)
                    navController.navigate(MovieAppScreen.MovieDetail.name)
                })
            }
            composable(
                route = MovieAppScreen.MovieDetail.name
            ) {
                MovieDetailScreen(viewModel = viewModel, onBackClick = {
                    navController.popBackStack(MovieAppScreen.MoviesList.name, inclusive = false)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridPreview() {
    /*val list = listOf(
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
    )*/
//    val item = MovieItem(
//        title = "Movie 1",
//        description = "hvfdjkdnvkjdfn",
//        photo = R.drawable.placeholder
//    )
//    MoviesByAtlysTheme {
//        MovieDetailScreen({})
//    }
}

