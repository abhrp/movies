package com.github.abhrp.mobile_ui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.abhrp.mobile_ui.R
import com.github.abhrp.mobile_ui.di.ViewModelFactory
import com.github.abhrp.mobile_ui.mapper.MovieMapper
import com.github.abhrp.presentation.model.MovieView
import com.github.abhrp.presentation.state.Resource
import com.github.abhrp.presentation.state.ResourceState
import com.github.abhrp.presentation.viewmodel.BrowseMoviesViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse_movies.*
import javax.inject.Inject

class BrowseMoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: BrowseMoviesAdapter
    @Inject
    lateinit var mapper: MovieMapper
    @Inject
    lateinit var viemodelFactory: ViewModelFactory
    @Inject
    lateinit var viewmodel: BrowseMoviesViewModel

    companion object {
        fun initActivity(context: Context): Intent {
            return Intent(context, BrowseMoviesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_browse_movies)
        viewmodel = ViewModelProviders.of(this, viemodelFactory).get(BrowseMoviesViewModel::class.java)
        setUpSwipeToRefreshView()
        setUpMoviesListView()
    }

    private fun setUpSwipeToRefreshView() {
        swipeToRefreshView.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        swipeToRefreshView.setOnRefreshListener {
            loadMovies()
        }
    }

    override fun onStart() {
        super.onStart()
        loadMovies()
    }

    private fun setUpMoviesListView() {
        moviesListView.layoutManager = LinearLayoutManager(this)
        moviesListView.adapter = adapter
    }

    private fun loadMovies() {
        viewmodel.getMovies().observe(this, Observer<Resource<List<MovieView>>> {
            it?.let {
                handleData(it)
            }
        })
        viewmodel.fetchMovies()
    }

    private fun handleData(resource: Resource<List<MovieView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                swipeToRefreshView.isRefreshing = false
                resource.data?.let {
                    adapter.movies = it.map {
                        mapper.mapToView(it)
                    }.toList()
                    adapter.notifyDataSetChanged()
                }

            }
            ResourceState.LOADING -> {
                swipeToRefreshView.isRefreshing = true
            }
            ResourceState.ERROR -> {
                swipeToRefreshView.isRefreshing = false
            }
        }

    }
}
