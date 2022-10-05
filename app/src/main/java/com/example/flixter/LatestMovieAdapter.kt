package com.example.flixter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "LatestMovieAdapter"

class LatestMovieAdapter (
    private val context: Context,
    private val movies: List<LatestMovie>,
    )
    : RecyclerView.Adapter<LatestMovieAdapter.ViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_latest_movie, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val movie = movies[position]
            holder.bind(movie)
        }

        override fun getItemCount() = movies.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            private val mMovieImage = itemView.findViewById<ImageView>(R.id.movie_image)
            private val mMovieTitle = itemView.findViewById<TextView>(R.id.movie_title)
            private val mMovieDesc = itemView.findViewById<TextView>(R.id.movie_description)
            private val mMoviePopularity = itemView.findViewById<TextView>(R.id.movie_popularity)

            init {
                itemView.setOnClickListener(this)
            }

            fun bind(movie: LatestMovie){
                mMovieTitle.text = movie.title
                mMovieDesc.text = movie.overview

                Glide.with(context)
                    .load(movie.movie_image_url)
                    .into(mMovieImage)
            }

            override fun onClick(p0: View?) {
                val movie = movies[absoluteAdapterPosition]
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(MOVIE_EXTRA, movie)
                context.startActivity(intent)
            }
        }
}

//
//holder.mItem = movie
//holder.mMovieTitle.text = movie.original_title
//holder.mMovieDesc.text = movie.overview
//val img = "https://image.tmdb.org/t/p/w500/" + movie.poster_path
//Glide.with(holder.mView)
//.load(img)
//.centerInside()
//.into(holder.mMovieImage)
//
//holder.mView.setOnClickListener {
//    holder.mItem?.let { book ->
//        mListener?.onItemClick(book)
//    }
//}