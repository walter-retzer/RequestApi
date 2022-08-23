package com.example.exercicio03.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cities(
    @SerializedName("cidade")
    var city: String,
    var latitude: Double,
    var longitude: Double
): Parcelable {

}
