package com.self.moviesbyatlys

import android.util.Log

class MoviesRepository {
    private val remoteSource = MoviesRemoteSource()
    suspend fun getMovies(): List<MovieItem>? {
        val movies = remoteSource.getMovies()
        val configuration = remoteSource.getImageConfiguration()
        movies?.map {
            it.poster = getMoviePoster(configuration.images.base_url, it.poster_path)
        }
        return movies
    }

    private suspend fun getMoviePoster(baseUrl: String, posterPath: String): String {
        val path = baseUrl + "original" + posterPath
        return remoteSource.getMoviePoster(path)
    }
}