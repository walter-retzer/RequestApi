package com.example.exercicio03.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.exercicio03.R
import com.example.exercicio03.data.Cities
import com.example.exercicio03.viewmodel.ApiViewModel

class ListCitiesActivity : AppCompatActivity() {

    private val recycler: RecyclerView
        get() = findViewById(R.id.recycler)

    private val model: ApiViewModel by viewModels()

    private val progressBar: ProgressBar
    get() = findViewById(R.id.loading)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cities)

        supportActionBar?.hide()

        model.loading.observe(this){
            progressBar.isVisible = it
        }
        model.getWeatherDetails()
        model.cityDetails.observe(this){ list->
            Log.d("Api List", "List Receive: $list")
            recycler.adapter = RecyclerViewAdapter(list) {
                sendToDetailCity(it)
            }
        }

//        model.getWeather().observe(this) { list ->
//            Log.d("Api List", "List Receive: $list")
//            recycler.adapter = RecyclerViewAdapter(list) {
//                sendToDetailCity(it)
//            }
//        }
    }

    private fun sendToDetailCity(city: Cities) {
        val intent = Intent(this, DetailsCityActivity::class.java).apply {
            putExtra("Details", city)
        }
        startActivity(intent)
    }
}
