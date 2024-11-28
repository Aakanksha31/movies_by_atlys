package com.self.moviesbyatlys.utils

import kotlinx.serialization.Serializable

@Serializable
object MoviesList

@Serializable
data class MovieDetail(val movieTitle: String, val movieDescription: String, val moviePoster: String)