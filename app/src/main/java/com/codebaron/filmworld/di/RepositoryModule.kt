package com.codebaron.filmworld.di

import com.codebaron.filmworld.repository.FilmsRepository
import com.codebaron.filmworld.repository.FilmsRepositoryImplementation
import com.codebaron.filmworld.services.EndPointProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providerNewsRepository(provider: EndPointProvider): FilmsRepository =
        FilmsRepositoryImplementation(provider)
}