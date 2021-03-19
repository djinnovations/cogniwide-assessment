package com.djphy.example.moviesgriddisplay.repo

import com.djphy.example.moviesgriddisplay.remote.NetworkResponse
import com.djphy.example.moviesgriddisplay.remote.network.models.MoviesList
import io.reactivex.Single

interface MoviesRepoI {

    fun getPopularMoviesList() : Single<NetworkResponse<MoviesList>>

}