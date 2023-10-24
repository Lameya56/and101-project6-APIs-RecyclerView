package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var petImageURL = ""
    private lateinit var petList: MutableList<String>
    private lateinit var rvPets: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDogImageURL()
        Log.d("petImageURL", "pet image URL set")
        rvPets = findViewById(R.id.pet_list)
        petList = mutableListOf()
    }
    private fun getDogImageURL() {
        val client = AsyncHttpClient()
        client["https://dog.ceo/api/breeds/image/random/20", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val petImageArray = json.jsonObject.getJSONArray("message")
                for (i in 0 until petImageArray.length()) {

                    petList.add(petImageArray.getString(i))
                }
                val adapter = PetAdapter(petList)
                rvPets.adapter = adapter
                rvPets.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("Dog", "response successful$json")
                petImageURL = json.jsonObject.getString("message")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }
    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            getDogImageURL()

            Glide.with(this)
                .load(petImageURL)
                .fitCenter()
                .into(imageView)
        }

    }
}