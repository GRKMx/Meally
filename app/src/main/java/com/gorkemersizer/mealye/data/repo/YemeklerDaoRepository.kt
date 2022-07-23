package com.gorkemersizer.mealye.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gorkemersizer.mealye.data.entity.*
import com.gorkemersizer.mealye.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository(var ydao: YemeklerDao) {
    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var sepetListesi: MutableLiveData<List<SepetYemekler>>

    init {
        yemeklerListesi = MutableLiveData()
        sepetListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    fun yemekEkle(yemek_ad: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) {
        ydao.sepeteYemekEkle(yemek_ad, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                val basari = response.body()!!.success
                val mesaj = response.body()!!.message
                Log.e("Yemek kayıt","$basari - $mesaj")
            }
            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }

    fun yemekSil(sepet_yemek_id: Int, kullanici_adi: String) {
        ydao.sepettenYemekSil(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                val basari = response.body()!!.success
                val mesaj = response.body()!!.message
                Log.e("Yemek sil","$basari - $mesaj")
                sepetiGetir(kullanici_adi)
            }
            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }

    fun sepetCagir(): MutableLiveData<List<SepetYemekler>>{
        return sepetListesi
    }

    fun sepetiGetir(kullanici_adi: String) {
        ydao.sepettekiYemekleriGetir(kullanici_adi).enqueue(object : Callback<SepetYemeklerCevap> {
            override fun onResponse(call: Call<SepetYemeklerCevap>, response: Response<SepetYemeklerCevap>) {
                val basari = response.body()!!.success
                val sepetListe = response.body()!!.sepet_yemekler
                Log.e("SepetGetir","$basari")
                sepetListesi.value = sepetListe
            }
            override fun onFailure(call: Call<SepetYemeklerCevap>, t: Throwable) {}
        })
    }

    fun tumYemekleriAl() {
        ydao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>, response: Response<YemeklerCevap>) {
                val liste = response.body()!!.yemekler
                yemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {}
        })
    }

    fun yemekAra(aramaKelimesi:String) {
        ydao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>, response: Response<YemeklerCevap>) {
                val liste = response.body()!!.yemekler
                val sonuc = liste.filter {
                    it.yemek_adi.contains(aramaKelimesi, true)
                }
                yemeklerListesi.value = sonuc
            }
            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {}
        })
    }

}