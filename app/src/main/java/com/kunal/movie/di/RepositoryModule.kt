package com.kunal.movie.di

import com.kunal.movie.data.network.apis.TMDbApi
import com.kunal.movie.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repository Module For
 * Dependency Injection
 * Using Dagger HILT
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        tmDbApi: TMDbApi
    ): MoviesRepository {
        return MoviesRepository(tmDbApi)
    }

}