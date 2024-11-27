package com.self.moviesbyatlys.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.self.moviesbyatlys.MovieItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private var _moviesList = MutableStateFlow<List<MovieItem>>(emptyList())
    val moviesList = _moviesList.asStateFlow()

    private var _selectedMovie = MutableStateFlow<MovieItem>(MovieItem.emptyState)
    val selectedMovie = _selectedMovie.asStateFlow()

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch {

        }
    }
}