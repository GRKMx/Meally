package com.gorkemersizer.mealye.data.entity

import com.google.gson.annotations.SerializedName

data class SepetYemeklerCevap (
    @SerializedName("success") val success: Int,
    @SerializedName("sepet_yemekler") val sepet_yemekler: List<SepetYemekler>
    )