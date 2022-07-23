package com.gorkemersizer.mealye.ui.screens.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.gorkemersizer.mealye.MainActivity
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.Theme_MeAlYe)
        setContentView(view)
    }
}