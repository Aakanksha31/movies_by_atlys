package com.self.moviesbyatlys

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface MoviesService {
    @GET("trending/movie/{time_window}")
    suspend fun getMovies(
        @Path("time_window") timeWindow: String,
        @Query("language") language: String
    ): ApiResponse

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationResponse
}