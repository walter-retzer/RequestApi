package com.example.exercicio03.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonFactory {
    fun build(): Gson = GsonBuilder().create()
}
