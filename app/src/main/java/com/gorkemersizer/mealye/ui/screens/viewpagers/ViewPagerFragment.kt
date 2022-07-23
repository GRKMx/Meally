package com.gorkemersizer.mealye.ui.screens.viewpagers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gorkemersizer.mealye.MainActivity
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.ui.adapter.OnBoardingViewPagerAdapter
import com.gorkemersizer.mealye.ui.screens.onboarding.VPFirstScreen
import com.gorkemersizer.mealye.ui.screens.onboarding.VPSecondScreen
import com.gorkemersizer.mealye.ui.screens.onboarding.VPThirdScreen
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (onBoardingFinished()){
            val intent= Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf<Fragment>(
            VPFirstScreen(),
            VPSecondScreen(),
            VPThirdScreen()
        )
        val adapter = OnBoardingViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        view.viewPager.adapter = adapter
        return view
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

}