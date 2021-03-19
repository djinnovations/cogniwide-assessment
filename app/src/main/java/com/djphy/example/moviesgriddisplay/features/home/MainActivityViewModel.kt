package com.djphy.example.weatherapp.feature.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.djphy.example.moviesgriddisplay.TAG
import com.djphy.example.moviesgriddisplay.features.home.MainActivityViewModelState
import com.djphy.example.moviesgriddisplay.repo.MoviesRepoI

class MainActivityViewModel(
    private val homeRepoI: MoviesRepoI,
    app: Application
) : BaseViewModel<MainActivityViewModelState>(app) {

    init {
        Log.i(TAG, "View Model creation")
    }

    override val mBaseStateObservable: MutableLiveData<MainActivityViewModelState> by lazy {
        MutableLiveData<MainActivityViewModelState>()
    }

    private var mBaseState = MainActivityViewModelState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getMoviesData() {
        mBaseState = mBaseState.copy(loading = true)
        homeRepoI.getPopularMoviesList()
            .subscribe({
                mBaseState = if (it.success) {
                    mBaseState.copy(loading = false, success = true, data = it.response)
                } else {
                    mBaseState.copy(loading = false, failure = true, message = it.networkError.message)
                }
            }, {
                Log.e(TAG, "${it.stackTrace}")
                mBaseState = mBaseState.copy(loading = false, failure = true, message = it.localizedMessage!!)
            }).apply {
                mBaseDisposables.add(this)
            }
    }

}