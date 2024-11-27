package com.self.moviesbyatlys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val repository = MoviesRepository()
    private var _moviesList = MutableStateFlow<VMState<List<MovieItem>>>(LoadingState())
    val moviesList = _moviesList.asStateFlow()

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesList.postSuccess(repository.getMovies().orEmpty())
        }
    }
}