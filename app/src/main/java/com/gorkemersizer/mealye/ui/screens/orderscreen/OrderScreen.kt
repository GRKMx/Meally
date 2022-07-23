package com.gorkemersizer.mealye.ui.screens.orderscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentOrderScreenBinding
import com.gorkemersizer.mealye.databinding.SepetCardDesignBinding
import com.gorkemersizer.mealye.ui.adapter.SepetAdapter
import com.gorkemersizer.mealye.ui.adapter.YemeklerAdapter
import com.gorkemersizer.mealye.util.Constants.Companion.USERNAME
import com.gorkemersizer.mealye.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main_view_pager.*
import kotlinx.android.synthetic.main.fragment_order_screen.*
import kotlinx.android.synthetic.main.sepet_card_design.*
import kotlinx.android.synthetic.main.sepet_card_design.view.*
import java.lang.Exception

@AndroidEntryPoint
class OrderScreen : Fragment() {
    private lateinit var binding: FragmentOrderScreenBinding
    private lateinit var viewModel: OrderScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_screen, container, false)
        binding.orderScreenFragment = this
        viewModel.sepetListesi.observe(viewLifecycleOwner) {
            val adapter = SepetAdapter(requireContext(), it, viewModel)
            binding.sepetAdapter = adapter
            binding.rvSepet.isVisible = true
        }
        viewModel.araToplam.observe(viewLifecycleOwner) {
            if (viewModel.araToplam.value==0){
                val getirmeUcreti = "0"
                binding.textViewGetirmeUcreti.text = getirmeUcreti
            } else {
                val getirmeUcreti = "8"
                binding.textViewGetirmeUcreti.text = getirmeUcreti
            }
            binding.textViewYemekTutar.text = "$it ₺"
            binding.textViewToplamFiyat.text = "${it+binding.textViewGetirmeUcreti.text.toString().toInt()}"
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: OrderScreenViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.sepetiGetirVM(USERNAME)
    }

    fun odemeyeGec(v:View){
        val viewPager = activity?.findViewById<ViewPager2>(R.id.mainViewPager)
        viewPager?.currentItem = 3
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetiGetirVM(USERNAME)
    }

    override fun onPause() {
        super.onPause()
        viewModel.araToplamiSifirla()
    }

    fun hepsiniSil (v: View) {
        for (i in viewModel.sepetListesi.value!!) {
            viewModel.yemekSilVM(i.sepet_yemek_id.toInt(), USERNAME)
            binding.rvSepet.isVisible = false
            binding.textViewYemekTutar.text = "0"
            binding.textViewGetirmeUcreti.text = "0"
            binding.textViewToplamFiyat.text = "0"
        }
    }

}