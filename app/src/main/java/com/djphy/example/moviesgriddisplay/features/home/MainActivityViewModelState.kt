package com.djphy.example.moviesgriddisplay.features.home

import com.djphy.example.moviesgriddisplay.remote.network.models.MoviesList


data class MainActivityViewModelState (
    var loading: Boolean = false,
    var failure: Boolean = false,
    var success: Boolean = false,
    var message: String = "",
    var data: MoviesList? = null
)