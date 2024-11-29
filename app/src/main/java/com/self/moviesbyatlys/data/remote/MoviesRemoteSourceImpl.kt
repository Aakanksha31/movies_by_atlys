package com.self.moviesbyatlys.data.remote

import com.self.moviesbyatlys.models.MovieItem
import com.self.moviesbyatlys.network.ConfigurationResponse
import javax.inject.Inject

class MoviesRemoteSourceImpl @Inject constructor(private val service: MoviesService) :
    MoviesRemoteSource {

    override suspend fun getMovies(): List<MovieItem>? = service.getMovies("week", "en-US").results
    override suspend fun getImageConfiguration(): ConfigurationResponse = service.getConfiguration()
}