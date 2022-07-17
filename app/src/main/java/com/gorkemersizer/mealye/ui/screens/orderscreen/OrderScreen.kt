package com.gorkemersizer.mealye.ui.screens.orderscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentOrderScreenBinding
import com.gorkemersizer.mealye.ui.adapter.SepetAdapter
import com.gorkemersizer.mealye.ui.adapter.YemeklerAdapter
import com.gorkemersizer.mealye.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint
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
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: OrderScreenViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.sepetiGetirVM("guts")
    }

    fun odemeyeGec(v:View){
        Navigation.gecisYap(v, R.id.action_orderScreen_to_cartScreen)
    }

}