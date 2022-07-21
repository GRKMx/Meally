package com.gorkemersizer.mealye.ui.adapter

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.core.graphics.alpha
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.data.entity.Yemekler
import com.gorkemersizer.mealye.databinding.YemeklerCardDesignBinding
import com.gorkemersizer.mealye.ui.screens.mainscreen.MainScreenViewModel
import com.gorkemersizer.mealye.util.Constants
import com.gorkemersizer.mealye.util.Constants.Companion.USERNAME
import com.squareup.picasso.Picasso
import retrofit2.http.Url
import java.util.*
import kotlin.collections.ArrayList

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
        //val colors: Array<String> = arrayOf("#90ffc0cb","#90ffff00","#907fffd4","#9000ffff","#90ff0000","#90ffa500","#908a2be2","#90ffdab9")

        val yemek = yemeklerListesi[position]
        val t = holder.binding

        /*
        when ((position+1)%5) {
            //1 -> t.satirCard.setBackgroundColor(Color.parseColor(colors[1]))
            //2 -> t.satirCard.setBackgroundColor(Color.parseColor(colors[2]))
            //3 -> t.satirCard.setBackgroundColor(Color.parseColor(colors[3]))
            //4 -> t.satirCard.setBackgroundColor(Color.parseColor(colors[4]))
            //5 -> t.satirCard.setBackgroundColor(Color.parseColor(colors[5]))
        }

         */

        t.yemekNesnesi = yemek
        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}").into(t.imageView2)
        t.progressBar.isVisible = false


        //t.satirCard.setCardBackgroundColor()

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
                viewModel.yemekEkleVM(yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat.toInt(), yemek.yemek_siparis_adet, USERNAME)
                yemek.yemek_siparis_adet = 0
                t.textViewAdet.text = yemek.yemek_siparis_adet.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

}