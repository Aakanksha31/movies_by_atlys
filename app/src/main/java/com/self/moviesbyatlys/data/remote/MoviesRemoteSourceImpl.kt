package com.self.moviesbyatlys.data.remote

import com.self.moviesbyatlys.models.MovieItem
import com.self.moviesbyatlys.network.ConfigurationResponse
import com.self.moviesbyatlys.network.RetrofitClient

class MoviesRemoteSourceImpl : MoviesRemoteSource {
    private val service = RetrofitClient.apiService

    override suspend fun getMovies(): List<MovieItem>? = service.getMovies("week", "en-US").results
    override suspend fun getImageConfiguration(): ConfigurationResponse = service.getConfiguration()
}