package com.self.moviesbyatlys

data class ApiResponse(
    val page: Int,
    val results: List<MovieItem>? = null
)

data class ConfigurationResponse(
    val images: ConfigData
)

data class ConfigData (
    val base_url: String,
    val poster_sizes: List<String>
)
