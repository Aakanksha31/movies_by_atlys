package com.self.moviesbyatlys.di

import com.self.moviesbyatlys.di.AppConstants.API_KEY
import com.self.moviesbyatlys.di.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    companion object {
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

        @Singleton
        @Provides
        fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}