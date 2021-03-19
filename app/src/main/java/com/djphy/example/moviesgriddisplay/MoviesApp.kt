package com.djphy.example.moviesgriddisplay

import android.app.Application
import com.djphy.example.moviesgriddisplay.di.AppComponent
import com.djphy.example.moviesgriddisplay.di.DaggerAppComponent
import com.djphy.example.moviesgriddisplay.di.DaggerDataComponent
import com.djphy.example.moviesgriddisplay.di.DataComponent

class MoviesApp : Application() {

    private val appComponent : AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

    private val dataComponent : DataComponent by lazy {
        DaggerDataComponent.create()
    }

    init {
        instance = this
    }

    companion object{
        private lateinit var instance: MoviesApp

        fun getApp() : MoviesApp {
            return instance
        }

        fun appComponent(): AppComponent {
            return instance.appComponent
        }

        fun dataComponent(): DataComponent {
            return instance.dataComponent
        }
    }
}