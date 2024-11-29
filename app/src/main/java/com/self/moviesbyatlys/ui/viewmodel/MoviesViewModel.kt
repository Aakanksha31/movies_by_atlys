package com.self.moviesbyatlys.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.self.moviesbyatlys.data.repo.MoviesRepository
import com.self.moviesbyatlys.models.MovieItem
import com.self.moviesbyatlys.models.MovieListUiState
import com.self.moviesbyatlys.models.OneOffEvent
import com.self.moviesbyatlys.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    private val _movieList = MutableStateFlow<List<MovieItem>>(emptyList())
    private val movieList: StateFlow<List<MovieItem>> = _movieList.asStateFlow()

    private val _oneOffEvent = Channel<OneOffEvent>()
    val oneOffEvent = _oneOffEvent.receiveAsFlow()

    init {
        getPosterConfiguration()
    }

    private fun getPosterConfiguration() {
        viewModelScope.safeLaunch({
            val baseUrl = repository.getConfiguration().base_url
            if (baseUrl.isEmpty()) {
                viewModelScope.launch {
                    _oneOffEvent.send(OneOffEvent.ShowError("Configuration Error"))
                }
            } else {
                getMoviesList(baseUrl)
            }
        }) { _, message ->
            _uiState.update {
                it.copy(
                    loading = false
                )
            }
            viewModelScope.launch {
                _oneOffEvent.send(OneOffEvent.ShowError(message))
            }
        }
    }

    private fun getMoviesList(baseUrl: String) {
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        viewModelScope.safeLaunch({
            _movieList.value = repository.getMovies(baseUrl).orEmpty()
            _uiState.update {
                it.copy(
                    loading = false, movies = movieList.value
                )
            }
        }) { _, message ->
            _uiState.update {
                it.copy(
                    loading = false
                )
            }
            viewModelScope.launch {
                _oneOffEvent.send(OneOffEvent.ShowError(message))
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        //local search
        _uiState.update {
            it.copy(query = query,
                movies = if (query.isEmpty()) {
                    movieList.value
                } else {
                    movieList.value.filter {
                        it.title.contains(
                            query
                        )
                    }
                })
        }
    }
}