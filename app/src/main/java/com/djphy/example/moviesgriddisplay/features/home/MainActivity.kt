package com.djphy.example.moviesgriddisplay.features.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.djphy.example.moviesgriddisplay.MoviesApp
import com.djphy.example.moviesgriddisplay.R
import com.djphy.example.moviesgriddisplay.createFactory
import com.djphy.example.moviesgriddisplay.databinding.ActivityMainBinding
import com.djphy.example.moviesgriddisplay.repo.MoviesRepoI
import com.djphy.example.moviesgriddisplay.toast
import com.djphy.example.weatherapp.feature.home.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var mDataBinding: ActivityMainBinding

    @Inject
    lateinit var homeRepoI: MoviesRepoI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        MoviesApp.dataComponent().inject(this)
        initVm()
        initAdapter()
        initDataProvider()
    }

    private fun initDataProvider() {
        mViewModel.getMoviesData()
    }

    private fun initAdapter() {
        recycler_view.apply {
            layoutManager = GridLayoutManager(
                this@MainActivity, 2,
                GridLayoutManager.VERTICAL, false
            )
            mAdapter = MoviesAdapter()
            adapter = mAdapter
        }
    }

    private fun initVm() {
        val factory = MainActivityViewModel(
            homeRepoI,
            application
        ).createFactory()
        mViewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        setObservers()
    }

    private fun setObservers() {
        mViewModel.mBaseStateObservable.observe(this, Observer {
            mDataBinding.state = it
            updateViews(it)
        })
    }

    private fun updateViews(state: MainActivityViewModelState) {
        when {
            state.success -> {
                mAdapter.submitList(state.data?.moviesList ?: emptyList())
            }
            state.failure -> {
                toast(state.message)
            }
        }
    }
}