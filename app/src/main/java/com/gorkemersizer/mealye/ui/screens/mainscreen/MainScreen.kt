package com.gorkemersizer.mealye.ui.screens.mainscreen

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.data.entity.Yemekler
import com.gorkemersizer.mealye.databinding.FragmentMainScreenBinding
import com.gorkemersizer.mealye.ui.adapter.YemeklerAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main_screen.view.*

@AndroidEntryPoint
class MainScreen : Fragment(){
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var viewModel: MainScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        binding.mainScreenFragment = this

        viewModel.yemeklerListesi.observe(viewLifecycleOwner) {
            val adapter = YemeklerAdapter(requireContext(), it, viewModel)
            binding.yemeklerAdapter = adapter
        }
        //val viewPager = activity?.findViewById<ViewPager2>(R.id.mainViewPager)
        //viewPager?.currentItem = 1
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