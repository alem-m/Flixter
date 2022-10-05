package com.example.flixter


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flixter.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}


private const val TAG = "MainActivity/"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val LATEST_MOVIE_URL =
    "https://api.themoviedb.org/3/movie/popular?api_key=${SEARCH_API_KEY}"

class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<LatestMovie>()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        moviesRecyclerView = findViewById(R.id.movies)

        val movieAdapter = LatestMovieAdapter(this, movies)
        moviesRecyclerView.adapter = movieAdapter

        moviesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            moviesRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        client.get(LATEST_MOVIE_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch movies: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched movies: $json")
                try {
                    // TODO: Create the parsedJSON
                    // TODO: AND Do something with the returned json (contains article information)
//                    val parsedJson = createJson().decodeFromString(
//                        Result.serializer(),
//                        json.jsonObject.toString()
//                    )
                    // TODO: Save the articles and reload the screen
//                    parsedJson.results {
//                        movies.addAll(movies)
//                        movieAdapter.notifyDataSetChanged()
//                    }
                    val resultsJSON = json.jsonObject.get("results").toString()
                    val gson = Gson()
                    val arrayMovieType = object : TypeToken<List<LatestMovie>>() {}.type

                    val models : List<LatestMovie> = gson.fromJson(resultsJSON, arrayMovieType)
                    moviesRecyclerView.adapter = LatestMovieAdapter(this@MainActivity, models)
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }
        })
    }
}