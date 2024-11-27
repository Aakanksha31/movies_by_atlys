package com.self.moviesbyatlys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val repository = MoviesRepository()
    private var _moviesList = MutableStateFlow<VMState<List<MovieItem>>>(LoadingState())
    val moviesList = _moviesList.asStateFlow()

    private var _selectedMovie = MutableStateFlow(MovieItem.emptyState)
    val selectedMovie = _selectedMovie.asStateFlow()

    init {
        getMoviesList()
    }

    fun setSelectedItem(item: MovieItem) {
        _selectedMovie.value = item
    }

    private fun getMoviesList() {
        viewModelScope.launch {
            _moviesList.postSuccess(repository.getMovies().orEmpty())
        }
    }
}