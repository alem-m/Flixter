package com.example.flixter

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var movieImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var popularityTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_latest_movie_detail)

        // TODO: Find the views for the screen
        movieImageView = findViewById(R.id.movie_image)
        titleTextView = findViewById(R.id.movie_title)
        popularityTextView = findViewById(R.id.movie_popularity)
        overviewTextView = findViewById(R.id.movie_description)
        // TODO: Get the extra from the Intent
        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as LatestMovie
        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = movie.title
        popularityTextView.text = movie.popularity
        overviewTextView.text = movie.overview
        // TODO: Load the media image
        Glide.with(this)
            .load(movie.movie_image_url)
            .into(movieImageView)
    }
}