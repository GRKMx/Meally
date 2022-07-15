package com.gorkemersizer.mealye.ui.screens.cartscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentCartScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartScreen : Fragment() {
    private lateinit var binding: FragmentCartScreenBinding
    private lateinit var viewModel: CartScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart_screen, container, false)
        binding.cartScreenFragment = this

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartScreenViewModel by viewModels()
        viewModel = tempViewModel
    }

}