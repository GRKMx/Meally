package com.gorkemersizer.mealye.ui.screens.orderdonescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentOrderDoneScreenBinding
import com.gorkemersizer.mealye.databinding.FragmentOrderScreenBinding
import com.gorkemersizer.mealye.util.gecisYap

class OrderDoneScreen : Fragment() {
    private lateinit var binding: FragmentOrderDoneScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_done_screen, container, false)
        binding.orderDoneScreenFragment = this

        return binding.root
    }

    fun siparisDoneReturn(v: View) {
        Navigation.gecisYap(v, R.id.action_orderDoneScreen_to_mainViewPagerFragment)
    }

}