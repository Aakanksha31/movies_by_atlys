package com.self.moviesbyatlys.data.remote

import com.self.moviesbyatlys.network.ApiResponse
import com.self.moviesbyatlys.network.ConfigurationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("trending/movie/{time_window}")
    suspend fun getMovies(
        @Path("time_window") timeWindow: String,
        @Query("language") language: String
    ): ApiResponse

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationResponse
}