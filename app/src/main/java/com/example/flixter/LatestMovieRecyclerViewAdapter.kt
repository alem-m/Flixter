package com.example.flixter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter.R.id

class LatestMovieRecyclerViewAdapter (
    private val movies: List<LatestMovie>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<LatestMovieRecyclerViewAdapter.MovieViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_latest_movie, parent, false)
            return MovieViewHolder(view)
        }

        inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            var mItem: LatestMovie? = null
            val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
            val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView
            val mMovieDesc: TextView = mView.findViewById<View>(id.movie_description) as TextView

            override fun toString(): String {
                return mMovieTitle.toString() + " '" + mMovieDesc.text + "'"
            }
        }
        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val movie = movies[position]

            holder.mItem = movie
            holder.mMovieTitle.text = movie.original_title
            holder.mMovieDesc.text = movie.overview
            val img = "https://image.tmdb.org/t/p/w500/" + movie.poster_path
            Glide.with(holder.mView)
                .load(img)
                .centerInside()
                .into(holder.mMovieImage)

            holder.mView.setOnClickListener {
                holder.mItem?.let { book ->
                    mListener?.onItemClick(book)
                }
            }
        }

        override fun getItemCount(): Int {
            return movies.size
        }
}