package com.gorkemersizer.mealye.ui.screens.viewpagers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.ui.adapter.MainViewPagerAdapter
import com.gorkemersizer.mealye.ui.screens.cartscreen.CartScreen
import com.gorkemersizer.mealye.ui.screens.mainscreen.MainScreen
import com.gorkemersizer.mealye.ui.screens.orderscreen.OrderScreen
import com.gorkemersizer.mealye.ui.screens.profilescreen.ProfileScreen
import kotlinx.android.synthetic.main.fragment_main_view_pager.view.*
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class MainViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_view_pager, container, false)

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

}