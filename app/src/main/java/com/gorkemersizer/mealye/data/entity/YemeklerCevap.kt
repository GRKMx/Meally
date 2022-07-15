package com.gorkemersizer.mealye.data.entity

import com.google.gson.annotations.SerializedName

data class YemeklerCevap(
    @SerializedName("success") val success: Int,
    @SerializedName("yemekler") val yemekler: List<Yemekler>
)