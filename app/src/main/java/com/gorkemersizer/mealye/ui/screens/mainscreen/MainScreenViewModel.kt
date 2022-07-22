package com.gorkemersizer.mealye.ui.screens.mainscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.gorkemersizer.mealye.data.entity.Yemekler
import com.gorkemersizer.mealye.data.repo.YemeklerDaoRepository
import com.gorkemersizer.mealye.ui.adapter.YemeklerAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MainScreenViewModel @Inject constructor(var yrepo: YemeklerDaoRepository) : ViewModel(){
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

    fun yemekleriYukle() {
        yrepo.tumYemekleriAl()
    }

    fun yemekEkleVM(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) {
        yrepo.yemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    fun adetSayisiniArtir(gelenAdet: Int): Int {
        return gelenAdet + 1
    }

}