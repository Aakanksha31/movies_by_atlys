package com.self.moviesbyatlys.models

import kotlinx.serialization.Serializable

@Serializable
data class MovieItem(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String,
    var poster: String
) {
    companion object {
        val emptyState = MovieItem(title = "", poster_path = "", overview = "", id = 0, poster = "")
    }
}

data class MovieListUiState(
    val loading: Boolean = false,
    val movies: List<MovieItem> = emptyList(),
    val query: String = ""
)