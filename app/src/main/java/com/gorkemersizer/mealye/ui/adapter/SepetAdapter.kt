package com.gorkemersizer.mealye.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.data.entity.SepetYemekler
import com.gorkemersizer.mealye.databinding.SepetCardDesignBinding
import com.gorkemersizer.mealye.ui.screens.orderscreen.OrderScreenViewModel
import com.gorkemersizer.mealye.util.Constants.Companion.USERNAME

class SepetAdapter(
    var mContext: Context,
    var sepetListesi: List<SepetYemekler>,
    var viewModel: OrderScreenViewModel
): RecyclerView.Adapter<SepetAdapter.CardViewHolder>() {

    inner class CardViewHolder (binding: SepetCardDesignBinding): RecyclerView.ViewHolder(binding.root) {
        var binding: SepetCardDesignBinding
        init {
            this.binding = binding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: SepetCardDesignBinding = DataBindingUtil.inflate(layoutInflater, R.layout.sepet_card_design, parent, false)
        return CardViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        //val auth= Firebase.auth
        //val kullaniciAdi = auth.currentUser!!.email!!.toString().lowercase().split("@").first()
        val sepetYemek = sepetListesi[position]
        val t = holder.binding
        t.sepetNesnesi = sepetYemek

        val araToplam = sepetYemek.yemek_siparis_adet.toInt()*sepetYemek.yemek_fiyat.toInt()
        t.textViewSepetAraToplam.text = "$araToplam ₺"

        //viewModel.araToplamiGetir()
        viewModel.araToplamiArtir(araToplam)

        //Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}").into(t.imageViewSepetYemek)
        t.buttonSilSepet.setOnClickListener {
            viewModel.yemekSilVM(sepetYemek.sepet_yemek_id.toInt(), USERNAME)
            //viewModel.araToplamiGetir(araToplam)
            viewModel.araToplamiCikar(araToplam)
            viewModel.araToplamiSifirla()
            viewModel.araToplamiGetir()
            if (sepetListesi.size==1){
                t.root.isVisible=false
            }
        }
    }
    override fun getItemCount(): Int {
        return sepetListesi.size
    }
}