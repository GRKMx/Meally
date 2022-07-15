package com.gorkemersizer.mealye.ui.screens.cartscreen

import androidx.lifecycle.ViewModel
import com.gorkemersizer.mealye.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(var yrepo: YemeklerDaoRepository): ViewModel() {
}