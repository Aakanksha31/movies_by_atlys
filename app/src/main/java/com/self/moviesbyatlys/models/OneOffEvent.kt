package com.self.moviesbyatlys.models

sealed class OneOffEvent {
    class ShowError(val message: String) : OneOffEvent()
}