package com.self.moviesbyatlys

import kotlinx.serialization.SerialName

data class ApiResponse(
    val page: Int,
    val results: List<MovieItem>? = null
)

data class ConfigurationResponse(
    @SerialName(value = "images")
    val images: ConfigData
)

data class ConfigData (
    val base_url: String,
    val poster_sizes: List<String>
)
