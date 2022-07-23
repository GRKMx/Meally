package com.gorkemersizer.mealye.ui.screens.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gorkemersizer.mealye.MainActivity
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.ActivityLoginScreenBinding
import com.gorkemersizer.mealye.databinding.ActivityMainBinding
import com.gorkemersizer.mealye.ui.screens.onboarding.OnBoardingActivity
import kotlinx.coroutines.MainScope

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MeAlYe)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth= Firebase.auth

        val currentUser=auth.currentUser

        if(currentUser!=null){
            val intent= Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun signInClicked(view: View){

        val email=binding.textViewEmailText.text.toString()
        val password=binding.textViewPasswordText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Enter email and password!", Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent=Intent(this@LoginScreenActivity, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@LoginScreenActivity,it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

    }
    fun signUpClicked(view: View){

        val email=binding.textViewEmailText.text.toString()
        val password=binding.textViewPasswordText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Enter email and password!", Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent= Intent(this@LoginScreenActivity, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@LoginScreenActivity,it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        // do nothing
    }
}