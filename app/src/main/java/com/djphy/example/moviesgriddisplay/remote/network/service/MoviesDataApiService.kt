package com.djphy.example.moviesgriddisplay.remote.network.service

import com.djphy.example.moviesgriddisplay.remote.network.models.MoviesList
import retrofit2.Call
import retrofit2.http.GET

interface MoviesDataApiService {

    @GET("3/movie/popular")
    fun getPopularMovies(): Call<MoviesList>
}