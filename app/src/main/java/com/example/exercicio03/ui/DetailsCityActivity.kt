package com.example.exercicio03.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio03.data.Cities
import com.example.exercicio03.databinding.ActivityDetailsCityBinding
import com.example.exercicio03.viewmodel.ApiViewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailsCityActivity : AppCompatActivity() {

    private var cities: Cities? = null
    private lateinit var binding: ActivityDetailsCityBinding
    private val model: ApiViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityDetailsCityBinding.inflate(layoutInflater)
        cities = intent.getParcelableExtra<Cities>("Details")
        Log.d("Longitude", "List Receive: ${cities?.latitude.toString()}")
        Log.d("Latitude", "List Receive: ${cities?.longitude.toString()}")

        binding.detailNameCity.text = cities?.city.toString().uppercase()
        binding.latitudeValue.text = cities?.latitude.toString()
        binding.longitudeValue.text = cities?.longitude.toString()
        binding.btnReturn.setOnClickListener { sendToListCities() }

        model.getWeatherOpen(
            latitude = cities?.latitude.toString(),
            longitude = cities?.longitude.toString()
        ).observe(this) {
            binding.weatherStatus.text = it.weather.first().description.uppercase()
            binding.titleTemperatureValue.text = "${it.main.temp}°C"
            binding.titleTemperatureMin.text = "Temp min ${it.main.tempMin}°C"
            binding.titleTemperatureValueMax.text = "Temp max ${it.main.tempMax}°C"
            binding.pressureValue.text = "${it.main.pressure} miliBar"
            binding.titleSensationTermicaValue.text = "${it.main.sensation}°C"
            binding.umidadeValue.text = "${it.main.humidity}%"
            binding.sunRiseValue.text =
                "${getDateTime(it.sys.sunrise.toString(), "HH:mm:ss")}"
            binding.dateTime.text =
                "${getDateTime(it.dataTime.toString(), "EEEE, dd MMMM yyyy - HH:mm:ss")}"
        }

        setContentView(binding.root)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(s: String, format: String): String? {
        return try {
            val sdf = SimpleDateFormat(format)
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            Log.e("Converter", "Error: $e")
            "Error"
        }
    }

    private fun sendToListCities() {
        val intent = Intent(this, ListCitiesActivity::class.java)
        startActivity(intent)
    }
}
