package com.self.moviesbyatlys.data.repo

import com.self.moviesbyatlys.models.MovieItem
import com.self.moviesbyatlys.network.ConfigData

interface MoviesRepository {
    suspend fun getMovies(baseUrl: String): List<MovieItem>?
    suspend fun getConfiguration(): ConfigData
}