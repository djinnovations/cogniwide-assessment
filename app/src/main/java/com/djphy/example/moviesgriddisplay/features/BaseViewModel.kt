package com.djphy.example.weatherapp.feature.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T>(application: Application) : AndroidViewModel(application) {

    protected val mBaseDisposables: CompositeDisposable = CompositeDisposable()

    abstract val mBaseStateObservable: MutableLiveData<T>


    protected open fun publishState(state: T){
        mBaseStateObservable.postValue(state)
    }

    override fun onCleared() {
        super.onCleared()
        mBaseDisposables.clear()
    }
}