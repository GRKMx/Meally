package com.gorkemersizer.mealye.ui.screens.cartscreen

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentCartScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cart_screen.*
import java.util.*

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

        binding.checkBox2.setOnCheckedChangeListener{ y, n ->
            if (n){
                binding.checkBox3.isChecked = false
            }
        }

        binding.checkBox3.setOnCheckedChangeListener{ y,n ->
            if (n) {
                binding.checkBox2.isChecked = false
                val calendar = Calendar.getInstance()
                val saat = calendar.get(Calendar.HOUR_OF_DAY)
                val dakika = calendar.get(Calendar.MINUTE)
                val timePicker: TimePickerDialog

                timePicker = TimePickerDialog(this.context, TimePickerDialog.OnTimeSetListener{ timePicker, i, i1 ->
                    checkBox3.text = "$i : $i1"
                }, saat, dakika, true)
                timePicker.setTitle("Saat Seçiniz")
                timePicker.setButton(DialogInterface.BUTTON_POSITIVE,"Ayarla",timePicker)
                timePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"İptal",timePicker)

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

}