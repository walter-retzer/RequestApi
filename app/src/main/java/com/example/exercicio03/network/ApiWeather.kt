package com.example.exercicio03.network

import com.example.exercicio03.data.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeather {

    @GET("weather?&units=metric&appid=0367c18096ac1d6da1f9e5153f0da2df&lang=pt_br")
    suspend fun getWeatherData(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
    ): WeatherData

    companion object {
        val api: ApiWeather by lazy {
            RetrofitFactory.build(
                "OpenWeatherMap",
                OkHttp.build(),
                GsonFactory.build()
            )
                .create(ApiWeather::class.java)
        }
    }
}
