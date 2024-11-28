package com.self.moviesbyatlys.network

import com.self.moviesbyatlys.data.remote.MoviesService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private const val BASE_URL =
        "https://api.themoviedb.org/3/"

    const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NmYxYTE0MWQ4NDM1NmFkMjk5NzU5NjVkNzViMDFhOCIsIm5iZiI6MTczMjcxNDUyMi41MTYyNjY4LCJzdWIiOiI2NzQ2ZTM1M2QyODE4MTIxMjIxZGFlNjciLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.K-5eUa7z1FTU59jB--aI8cJF27cAhRBJRLKTCl9hbVI"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val apiKeyInterceptor = Interceptor { chain ->
        val originalRequest: Request = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer $API_KEY") // Add API key as a header
            .build()
        chain.proceed(modifiedRequest)
    }

    private val client =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MoviesService = retrofit.create(MoviesService::class.java)
}