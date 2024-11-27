package com.self.moviesbyatlys

import kotlinx.serialization.Serializable

@Serializable
object MoviesList

@Serializable
data class MovieDetail(val movieTitle: String, val movieDescription: String, val moviePoster: String)