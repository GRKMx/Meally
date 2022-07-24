package com.gorkemersizer.mealye.ui.screens.cartscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorkemersizer.mealye.data.entity.SepetYemekler
import com.gorkemersizer.mealye.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(var yrepo: YemeklerDaoRepository): ViewModel() {
    var sepetListesi = MutableLiveData<List<SepetYemekler>>()
    init {
        sepetListesi = yrepo.sepetCagir()
    }

    fun yemekSilVM(sepet_yemek_id: Int, kullanici_adi: String) {
        yrepo.yemekSil(sepet_yemek_id, kullanici_adi)
    }
}