package com.gorkemersizer.mealye.ui.screens.cartscreen

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentCartScreenBinding
import com.gorkemersizer.mealye.util.Constants
import com.gorkemersizer.mealye.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cart_screen.*
import kotlinx.android.synthetic.main.fragment_main_view_pager.*
import java.util.*
import kotlin.math.log

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

        binding.radioButtonIerdeGelsin.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                val calendar = Calendar.getInstance()
                val saat = calendar.get(Calendar.HOUR_OF_DAY)
                val dakika = calendar.get(Calendar.MINUTE)
                val timePicker: TimePickerDialog

                timePicker = TimePickerDialog(this.context, TimePickerDialog.OnTimeSetListener{ timePicker, i, i1 ->
                    radioButtonIerdeGelsin.text = "$i : $i1"
                }, saat, dakika, true)
                timePicker.setTitle("Choose Time")
                timePicker.setButton(DialogInterface.BUTTON_POSITIVE,"Set",timePicker)
                timePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",timePicker)

                timePicker.show()
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartScreenViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun siparisDone(v: View) {
        if (binding.editTextAdres.text.toString() == "" || binding.editTextAdres.text.toString().isEmpty() ||  binding.editTextAdres.text.toString().isBlank() ){
            Toast.makeText(context,"Enter delivery address", Toast.LENGTH_LONG).show()
        } else {
            for (i in viewModel.sepetListesi.value!!) {
                viewModel.yemekSilVM(i.sepet_yemek_id.toInt(), Constants.USERNAME)
                
            }
            Navigation.gecisYap(v, R.id.action_mainViewPagerFragment_to_orderDoneScreen)
        }
    }

}