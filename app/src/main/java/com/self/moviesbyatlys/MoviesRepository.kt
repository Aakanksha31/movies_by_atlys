package com.self.moviesbyatlys

import android.util.Log

class MoviesRepository {
    private val remoteSource = MoviesRemoteSource()
    suspend fun getMovies(): List<MovieItem>? {
        val movies = remoteSource.getMovies()
        val configuration = remoteSource.getImageConfiguration()
        movies?.map {
            it.poster = configuration.images.base_url +"original"+ it.poster_path
        }
        return movies
    }
}