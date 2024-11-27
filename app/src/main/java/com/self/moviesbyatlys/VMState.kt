package com.self.moviesbyatlys

import kotlinx.coroutines.flow.MutableStateFlow

sealed class VMState<V>(open val data: V? = null) {
    fun unwrap(): V = data ?: throw Exception("ERROR_NO_DATA_FOUND")
}

fun <T> MutableStateFlow<VMState<T>>.postSuccess(data: T) {
    value = SuccessState(data)
}

fun <T> MutableStateFlow<VMState<T>>.postLoading(data: T? = null) {
    value = LoadingState(data)
}

fun <T> MutableStateFlow<VMState<T>>.postError(errorCode: Int, errorMessage: String, data: T? = null) {
    value = ErrorState(errorCode, errorMessage, data)
}

class LoadingState<V>(data: V? = null) : VMState<V>(data)

class SuccessState<V>(override val data: V) : VMState<V>(data)

class ErrorState<V>(val errorCode: Int,val errorMessage: String, override val data: V? = null) : VMState<V>()