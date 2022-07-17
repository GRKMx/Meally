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

    init {
        //sepetiGetirVM("guts")
        sepetListesi = yrepo.sepetCagir()
    }

    fun yemekSilVM(sepet_yemek_id: Int, kullanici_adi: String) {
        yrepo.yemekSil(sepet_yemek_id, kullanici_adi)
    }

    fun sepetiGetirVM(kullanici_adi: String) {
        yrepo.sepetiGetir(kullanici_adi)
    }

}