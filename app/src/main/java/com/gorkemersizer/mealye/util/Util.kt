package com.gorkemersizer.mealye.util

import android.view.View
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.gecisYap(v:View, id:Int) {
    findNavController(v).navigate(id)
}

fun Navigation.gecisYap(v:View,id: NavDirections){
    findNavController(v).navigate(id)
}
