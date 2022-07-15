package com.gorkemersizer.mealye.retrofit

import com.gorkemersizer.mealye.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit

class ApiUtils {
    companion object {
        fun getYemeklerDao(): YemeklerDao {
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDao::class.java)
        }
    }
}