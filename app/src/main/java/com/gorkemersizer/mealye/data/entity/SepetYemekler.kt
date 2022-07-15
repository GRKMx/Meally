package com.gorkemersizer.mealye.data.entity

import com.google.gson.annotations.SerializedName

data class SepetYemekler(
    @SerializedName("sepet_yemek_id") val sepet_yemek_id: String,
    @SerializedName("yemek_adi") val yemek_adi: String,
    @SerializedName("yemek_fiyat") val yemek_fiyat: String,
    @SerializedName("yemek_resim_adi") val yemek_resim_adi: String,
    @SerializedName("kullanici_adi") val kullanici_adi: String,
    @SerializedName("yemek_siparis_adet") var yemek_siparis_adet: String
)