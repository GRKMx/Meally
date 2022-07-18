package com.gorkemersizer.mealye.ui.screens.viewpagers

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentMainViewPagerBinding
import com.gorkemersizer.mealye.ui.adapter.MainViewPagerAdapter
import com.gorkemersizer.mealye.ui.screens.cartscreen.CartScreen
import com.gorkemersizer.mealye.ui.screens.mainscreen.MainScreen
import com.gorkemersizer.mealye.ui.screens.orderscreen.OrderScreen
import com.gorkemersizer.mealye.ui.screens.profilescreen.ProfileScreen
import kotlinx.android.synthetic.main.fragment_main_view_pager.*
import kotlinx.android.synthetic.main.fragment_main_view_pager.view.*
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class MainViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentMainViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainViewPagerBinding.inflate(inflater)
        val view = binding.root

        val fragmentList = arrayListOf<Fragment>(
            MainScreen(),
            OrderScreen(),
            CartScreen(),
            ProfileScreen()
        )

        val adapter = MainViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        view.mainViewPager.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.tabLayout
        TabLayoutMediator(tabLayout, mainViewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_main_screen)
                1 -> tab.setIcon(R.drawable.ic_order_screen)
                2 -> tab.setIcon(R.drawable.ic_cart_screen)
                3 -> tab.setIcon(R.drawable.ic_profile_screen)
            }
        }.attach()
        super.onViewCreated(view, savedInstanceState)
    }

}