package com.gorkemersizer.mealye.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.data.entity.SepetYemekler
import com.gorkemersizer.mealye.databinding.SepetCardDesignBinding
import com.gorkemersizer.mealye.ui.screens.orderscreen.OrderScreenViewModel
import com.squareup.picasso.Picasso
import java.lang.Exception
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
        val sepetYemek = sepetListesi[position]
        val t = holder.binding
        t.sepetNesnesi = sepetYemek

        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}").into(t.imageViewSepetYemek)
        t.buttonSilSepet.setOnClickListener {
            viewModel.yemekSilVM(sepetYemek.sepet_yemek_id.toInt(), kullanici_adi = "guts")
            //notifyItemRemoved(position)
            //notifyDataSetChanged()
            //viewModel.sepetListesi.value = viewModel.yrepo.sepetCagir().value
            viewModel.sepetiGetirVM("guts")
        }
    }
    override fun getItemCount(): Int {
        return sepetListesi.size
    }
}