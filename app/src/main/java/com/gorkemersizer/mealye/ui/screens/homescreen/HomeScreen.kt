package com.gorkemersizer.mealye.ui.screens.homescreen

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentHomeScreenBinding
import com.gorkemersizer.mealye.util.Constants
import com.gorkemersizer.mealye.util.Constants.Companion.USERNAME
import com.gorkemersizer.mealye.util.gecisYap
import kotlinx.android.synthetic.main.fragment_main_view_pager.*

class HomeScreen : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val auth= Firebase.auth
        //val kullaniciAdi = auth.currentUser!!.email!!.toString().lowercase().split("@").first()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)
        binding.homeScreenFragment = this

        binding.welcomeMessage = "Merhaba, ${USERNAME}"
        binding.textViewWelcomeName.setTextColor(Color.BLACK)

        return binding.root
    }

}