package com.self.moviesbyatlys

import kotlinx.coroutines.withContext

class MoviesRemoteSource {
    private val service = RetrofitClient.apiService

    suspend fun getMovies(): List<MovieItem>? = service.getMovies("week", "en-US").results
    suspend fun getImageConfiguration() = service.getConfiguration()
}