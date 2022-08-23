package com.example.exercicio03.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    var name: String,
    var temperature: Double = 18.00,
    var temperatureMin: Double = 18.00,
    var temperatureMax: Double = 18.00,
    var sensationTemperature: Int = 23,
    var sunRise: String = "06:54",
    var pressure: Int = 12,
    var humidity: Int = 88,
    var latitude: Double = 12.4566,
    var longitude: Double = -35.999,
    var weatherStatus: String = "Chuva"
) : Parcelable {

}