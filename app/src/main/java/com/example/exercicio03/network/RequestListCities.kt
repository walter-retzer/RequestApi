package com.example.exercicio03.network

import com.example.exercicio03.data.Cities
import retrofit2.http.GET

interface RequestListCities {

    @GET("cidades.json")
    suspend fun getListCities(): ArrayList<Cities>

    companion object{
        val api: RequestListCities by lazy {
            RetrofitFactory.build(
                "City",
                OkHttp.build(),
                GsonFactory.build()
            ).create(RequestListCities::class.java)
        }
    }
}
