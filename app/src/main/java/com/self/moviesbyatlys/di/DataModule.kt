package com.self.moviesbyatlys.di

import com.self.moviesbyatlys.data.remote.MoviesRemoteSource
import com.self.moviesbyatlys.data.remote.MoviesRemoteSourceImpl
import com.self.moviesbyatlys.data.repo.MoviesRepository
import com.self.moviesbyatlys.data.repo.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    abstract fun moviesRepository(item: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun moviesRemoteSource(item: MoviesRemoteSourceImpl): MoviesRemoteSource

}