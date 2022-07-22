package com.gorkemersizer.mealye.ui.screens.detailscreen

import androidx.lifecycle.ViewModel
import com.gorkemersizer.mealye.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(var yrepo: YemeklerDaoRepository) : ViewModel(){

    fun yemekEkleVM(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) {
        yrepo.yemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    fun adetSayisiniArtir(gelenAdet: Int): Int {
        return gelenAdet + 1
    }

}