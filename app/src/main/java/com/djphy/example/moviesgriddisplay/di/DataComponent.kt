package com.djphy.example.moviesgriddisplay.di

import com.djphy.example.moviesgriddisplay.features.home.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class
    ]
)
interface DataComponent {
    fun inject(mainActivity: MainActivity)
}