package com.self.moviesbyatlys.data.repo

import com.self.moviesbyatlys.data.remote.MoviesRemoteSource
import com.self.moviesbyatlys.models.MovieItem
import com.self.moviesbyatlys.network.ConfigData
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val remoteSource: MoviesRemoteSource) :
    MoviesRepository {

    override suspend fun getConfiguration(): ConfigData =
        remoteSource.getImageConfiguration().images

    override suspend fun getMovies(baseUrl: String): List<MovieItem>? {
        val movies = remoteSource.getMovies()
        movies?.map {
            it.poster = baseUrl + "original" + it.poster_path
        }
        return movies
    }
}