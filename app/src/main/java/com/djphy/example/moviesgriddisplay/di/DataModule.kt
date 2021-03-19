package com.djphy.example.moviesgriddisplay.di

import com.djphy.example.moviesgriddisplay.repo.MoviesRepo
import com.djphy.example.moviesgriddisplay.repo.MoviesRepoI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {

    @Singleton
    @Provides
    fun provideHomeRepositoryI(): MoviesRepoI {
        return MoviesRepo()
    }
}