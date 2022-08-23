package com.example.exercicio03.repository

import com.example.exercicio03.data.Cities
import com.example.exercicio03.data.WeatherData
import com.example.exercicio03.network.ApiWeather
import com.example.exercicio03.network.RequestListCities
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(
    private val api: RequestListCities = RequestListCities.api,
    private val apiOpenWeather: ApiWeather = ApiWeather.api,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {

    fun requestListCities() = flow<ArrayList<Cities>> {
        val response: ArrayList<Cities> = api.getListCities()
        emit(response)
    }.flowOn(dispatcher)

    fun requestOpenWeather(latitude: String, longitude: String) = flow<WeatherData> {
        val response: WeatherData = apiOpenWeather.getWeatherData(latitude, longitude)
        emit(response)
    }.flowOn(dispatcher)

    companion object{
        val instance: Repository by lazy { Repository() }
    }
}
