package com.gorkemersizer.mealye.ui.screens.profilescreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentProfileScreenBinding
import com.gorkemersizer.mealye.ui.screens.loginscreen.LoginScreenActivity

class ProfileScreen : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentProfileScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_screen, container, false)
        binding.profileScreenFragment = this
        //binding.textViewAdreslerim.isVisible = false
        auth= Firebase.auth
        return binding.root
    }

    fun signOutClick(v: View) {
        auth.signOut()
        val intent= Intent(this.context, LoginScreenActivity::class.java)
        startActivity(intent)
    }

}