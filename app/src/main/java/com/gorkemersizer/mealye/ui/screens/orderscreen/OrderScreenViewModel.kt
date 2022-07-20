package com.gorkemersizer.mealye.ui.screens.orderscreen

import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.gorkemersizer.mealye.data.entity.SepetYemekler
import com.gorkemersizer.mealye.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModel @Inject constructor(var yrepo: YemeklerDaoRepository) : ViewModel() {

    var sepetListesi = MutableLiveData<List<SepetYemekler>>()
    var araToplam = MutableLiveData<Int>()

    init {
        sepetListesi = yrepo.sepetCagir()
        araToplam = MutableLiveData(0)
    }

    fun yemekSilVM(sepet_yemek_id: Int, kullanici_adi: String) {
        yrepo.yemekSil(sepet_yemek_id, kullanici_adi)
    }

    fun sepetiGetirVM(kullanici_adi: String) {
        yrepo.sepetiGetir(kullanici_adi)
    }

    fun araToplamiSifirla(){
        araToplam.value = 0
    }

    fun araToplamiGetir() {
        araToplam.value
    }

    fun araToplamiArtir(gelen: Int) {
        araToplam.value = araToplam.value?.plus(gelen)
    }

    fun araToplamiCikar(gelen: Int) {
        araToplam.value = araToplam.value?.minus(gelen)
    }

}