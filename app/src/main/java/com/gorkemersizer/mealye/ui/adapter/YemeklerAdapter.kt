package com.gorkemersizer.mealye.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.data.entity.Yemekler
import com.gorkemersizer.mealye.databinding.YemeklerCardDesignBinding
import com.gorkemersizer.mealye.ui.screens.mainscreen.MainScreenViewModel
import com.squareup.picasso.Picasso
import retrofit2.http.Url

class YemeklerAdapter(
    var mContext: Context,
    var yemeklerListesi: List<Yemekler>,
    var viewModel: MainScreenViewModel,
): RecyclerView.Adapter<YemeklerAdapter.CardViewHolder>() {
    inner class CardViewHolder(binding: YemeklerCardDesignBinding): RecyclerView.ViewHolder(binding.root) {
        var binding: YemeklerCardDesignBinding
        init {
            this.binding = binding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: YemeklerCardDesignBinding = DataBindingUtil.inflate(layoutInflater, R.layout.yemekler_card_design, parent, false)

        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val yemek = yemeklerListesi[position]
        val t = holder.binding
        t.yemekNesnesi = yemek

        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}").into(t.imageView2)

        t.imageViewDecreaseButton.setOnClickListener {
            if (yemek.yemek_siparis_adet > 0) {
                yemek.yemek_siparis_adet --
            }
            t.textViewAdet.text = yemek.yemek_siparis_adet.toString()
        }

        t.imageViewIncreaseButton.setOnClickListener {

            yemek.yemek_siparis_adet = viewModel.adetSayisiniArtir(yemek.yemek_siparis_adet)
            t.textViewAdet.text = yemek.yemek_siparis_adet.toString()

        }

        t.buttonAdd.setOnClickListener {
            if (yemek.yemek_siparis_adet > 0) {
                viewModel.yemekEkleVM(yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat.toInt(), yemek.yemek_siparis_adet, kullanici_adi = "guts")
                yemek.yemek_siparis_adet = 0
                t.textViewAdet.text = yemek.yemek_siparis_adet.toString()
            }
        }

    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}