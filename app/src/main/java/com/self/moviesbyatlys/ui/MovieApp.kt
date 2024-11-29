package com.self.moviesbyatlys.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.self.moviesbyatlys.ui.movie_detail.MovieDetailScreen
import com.self.moviesbyatlys.ui.movie_list.MovieListScreen
import com.self.moviesbyatlys.ui.viewmodel.MoviesViewModel
import com.self.moviesbyatlys.utils.MovieDetail
import com.self.moviesbyatlys.utils.MoviesList

@Composable
fun MovieApp(
    viewModel: MoviesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
    ) { innerPadding ->
        //compose navigation
        NavHost(
            navController = navController,
            startDestination = MoviesList,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<MoviesList> {
                MovieListScreen(viewModel, onItemClicked = { item ->
                    navController.navigate(
                        MovieDetail(
                            movieTitle = item.title,
                            movieDescription = item.overview,
                            moviePoster = item.poster
                        )
                    )
                })
            }
            composable<MovieDetail> { navBackStackEntry ->
                val item: MovieDetail = navBackStackEntry.toRoute()
                MovieDetailScreen(selectedMovie = item, onBackClick = {
                    navController.popBackStack(MoviesList, inclusive = false)
                })
            }
        }
    }
}