package com.github.abhrp.mobile_ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.abhrp.mobile_ui.R
import kotlinx.android.synthetic.main.activity_browse_movies.*

class BrowseMoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_movies)
        setUpMoviesListView()
    }

    private fun setUpMoviesListView() {
        moviesListView.layoutManager = LinearLayoutManager(this)
    }
}
