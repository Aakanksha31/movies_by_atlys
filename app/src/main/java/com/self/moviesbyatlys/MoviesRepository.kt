package com.self.moviesbyatlys

import android.util.Log

class MoviesRepository {
    private val remoteSource = MoviesRemoteSource(  )
    suspend fun getMovies(): List<MovieItem>? {
        val movies = remoteSource.getMovies()
        movies?.map {
            it.poster = getMoviePoster(it.poster_path)
        }
        return movies
    }

    private suspend fun getMoviePoster(posterPath: String): String  {
        val configuration = remoteSource.getImageConfiguration()
        Log.d("pathpath", configuration.images.toString())
        val path = configuration.images.base_url + "original/"+posterPath
        return remoteSource.getMoviePoster(path)
    }
}