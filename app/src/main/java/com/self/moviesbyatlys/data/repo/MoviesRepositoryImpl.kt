package com.self.moviesbyatlys.data.repo

import com.self.moviesbyatlys.data.remote.MoviesRemoteSourceImpl
import com.self.moviesbyatlys.models.MovieItem
import com.self.moviesbyatlys.network.ConfigData

class MoviesRepositoryImpl : MoviesRepository {
    private val remoteSource = MoviesRemoteSourceImpl()

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