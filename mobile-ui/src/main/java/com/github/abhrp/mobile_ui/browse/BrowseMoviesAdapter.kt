package com.github.abhrp.mobile_ui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.abhrp.mobile_ui.R
import com.github.abhrp.mobile_ui.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import javax.inject.Inject

class BrowseMoviesAdapter @Inject constructor() : RecyclerView.Adapter<BrowseMoviesAdapter.ViewHolder>() {

    var movies: List<Movie> = arrayListOf()
    var clickListener: MovieClickListener? = null

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

    inner class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(movie: Movie) {
            movieName.text = movie.title
            moviePopularity.text = movie.popularity.toString()
            val shortlistIcon = if (movie.isShortlisted) R.drawable.ic_shortlisted else R.drawable.ic_not_shortlisted
            movieShortlistView.setImageResource(shortlistIcon)
            movieShortlistView.setOnClickListener {
                clickListener?.onShortlistIconClicked(movie.id, movie.isShortlisted)
            }
            Picasso.get().load(movie.posterPath).placeholder(R.drawable.ic_placeholder).into(moviePoster)
        }
    }
}