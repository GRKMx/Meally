package com.gorkemersizer.mealye.ui.screens.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentMainScreenBinding
import com.gorkemersizer.mealye.ui.adapter.YemeklerAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var viewModel: MainScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        binding.mainScreenFragment = this

        viewModel.yemeklerListesi.observe(viewLifecycleOwner) {
            val adapter = YemeklerAdapter(requireContext(), it, viewModel)
            binding.yemeklerAdapter = adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainScreenViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }

}