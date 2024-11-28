package com.self.moviesbyatlys.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

const val INTERNET_NOT_FOUND = 502
const val UNKNOWN_ERROR = 999

fun CoroutineScope.safeLaunch(
    launchBody: suspend () -> Unit,
    errorBody: (errorCode: Int, message: String) -> Unit
): Job {
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (isUnReachException(throwable)) {
            errorBody.invoke(
                INTERNET_NOT_FOUND,
                "No Internet"
            )
        } else {
            errorBody.invoke(UNKNOWN_ERROR, "Something Went wrong")
        }

        Log.d("SAFE_LAUNCH", throwable.stackTraceToString())
    }
    return this.launch(coroutineExceptionHandler) {
        launchBody.invoke()
    }
}


private fun isUnReachException(e: Throwable): Boolean {
    return e is SocketTimeoutException ||
            e is SocketException ||
            e is UnknownHostException ||
            (e is HttpException)
}

@Composable
fun <T> ObserveAsOneOffEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(onEvent)
        }
    }
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}