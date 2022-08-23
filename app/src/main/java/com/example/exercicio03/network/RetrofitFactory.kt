package com.example.exercicio03.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    fun build(api: String, client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(
                when (api) {
                    "City" -> "https://s3.amazonaws.com/br.com.xds.challenge/"
                    "OpenWeatherMap" -> "https://api.openweathermap.org/data/2.5/"
                    else -> "https://"
                }
            )
            .build()
    }
}
