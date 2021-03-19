package com.djphy.example.moviesgriddisplay.repo

import com.djphy.example.moviesgriddisplay.MoviesApp
import com.djphy.example.moviesgriddisplay.remote.GenericNetworkResponseSingle
import com.djphy.example.moviesgriddisplay.remote.NetworkResponse
import com.djphy.example.moviesgriddisplay.remote.network.service.MoviesDataApiService
import com.djphy.example.moviesgriddisplay.remote.network.models.MoviesList
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepo: MoviesRepoI {

    @Inject
    lateinit var apiService: MoviesDataApiService

    init {
        MoviesApp.appComponent().inject(this)
    }

    override fun getPopularMoviesList(): Single<NetworkResponse<MoviesList>> {
        val apiCall = apiService.getPopularMovies()
        return GenericNetworkResponseSingle(apiCall, MoviesList::class)
    }

}