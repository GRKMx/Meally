package com.gorkemersizer.mealye.util

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Constants {

    companion object {
        const val BASE_URL = "http://kasimadalan.pe.hu/"

        val auth= Firebase.auth
        val kullaniciAdi = auth.currentUser!!.email!!.toString().lowercase().split("@").first()
        val USERNAME = kullaniciAdi
        val USEREMAIL = auth.currentUser!!.email!!.toString()
    }

}