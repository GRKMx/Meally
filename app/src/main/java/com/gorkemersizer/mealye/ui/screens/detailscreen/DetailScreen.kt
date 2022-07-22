package com.gorkemersizer.mealye.ui.screens.detailscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentDetailScreenBinding
import com.gorkemersizer.mealye.ui.screens.mainscreen.MainScreenViewModel
import com.gorkemersizer.mealye.util.Constants.Companion.USERNAME
import com.gorkemersizer.mealye.util.gecisYap
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {
    private lateinit var binding: FragmentDetailScreenBinding
    private lateinit var viewModel: DetailScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_screen, container, false)
        binding.detailScreenFragment = this

        val bundle: DetailScreenArgs by navArgs()
        val gelenYemek = bundle.yemek
        binding.yemekNesnesi = gelenYemek
        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}").into(binding.imageView2)

        binding.buttonAnaEkrana.setOnClickListener {
            Navigation.gecisYap(binding.buttonAnaEkrana, R.id.anaEkranaGecis)
        }
        binding.imageViewCikarmaYap.setOnClickListener {
            if (gelenYemek.yemek_siparis_adet > 0) {
                gelenYemek.yemek_siparis_adet --
            }
            binding.textView10.text = gelenYemek.yemek_siparis_adet.toString()
        }
        binding.imageViewToplamaYap.setOnClickListener {
            gelenYemek.yemek_siparis_adet = viewModel.adetSayisiniArtir(gelenYemek.yemek_siparis_adet)
            binding.textView10.text = gelenYemek.yemek_siparis_adet.toString()
        }
        binding.buttonEkle.setOnClickListener {
            if (gelenYemek.yemek_siparis_adet>0) {
                viewModel.yemekEkleVM(gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat.toInt(),gelenYemek.yemek_siparis_adet,USERNAME)
                gelenYemek.yemek_siparis_adet = 0
                binding.textView10.text = gelenYemek.yemek_siparis_adet.toString()
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailScreenViewModel by viewModels()
        viewModel = tempViewModel
    }

}