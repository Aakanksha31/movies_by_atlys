package com.self.moviesbyatlys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.self.moviesbyatlys.ui.MovieApp
import com.self.moviesbyatlys.ui.theme.MoviesByAtlysTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MoviesByAtlysTheme {
                MovieApp()
            }
        }
    }
}

