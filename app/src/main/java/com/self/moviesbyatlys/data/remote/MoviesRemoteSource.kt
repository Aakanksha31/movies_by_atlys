package com.self.moviesbyatlys.data.remote

import com.self.moviesbyatlys.models.MovieItem
import com.self.moviesbyatlys.network.ConfigurationResponse

interface MoviesRemoteSource {
    suspend fun getMovies(): List<MovieItem>?
    suspend fun getImageConfiguration(): ConfigurationResponse
}