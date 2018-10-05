package com.github.abhrp.mobile_ui.shortlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.abhrp.mobile_ui.R
import com.github.abhrp.mobile_ui.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_shortlisted_movie.*
import javax.inject.Inject

class ShortlistedMoviesAdapter @Inject constructor() : RecyclerView.Adapter<ShortlistedMoviesAdapter.ViewHolder>() {

    var movies: List<Movie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(movie: Movie) {
            movieName1.text = movie.title
            moviePopularity1.text = movie.popularity.toString()
            Picasso.get().load(movie.posterPath).placeholder(R.drawable.ic_placeholder).into(moviePoster1)
        }
    }
}