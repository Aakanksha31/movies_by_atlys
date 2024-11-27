package com.self.moviesbyatlys

data class MovieItem (
    val title: String,
    val photo: Int,
    val description: String
) {
   companion object {
       val emptyState = MovieItem(title = "", photo = 0, description = "")
   }
}