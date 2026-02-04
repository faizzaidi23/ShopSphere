package com.example.shopshere.UserInterface.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopshere.data.repository.ProductRepository
import com.example.shopshere.domain.model.Product
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel(){
    private val repository= ProductRepository()
    var products by mutableStateOf<List<Product>>(emptyList()) // Here we are not using mutableStateListOf because the items on the homeScreen will not be coming one by one
        //using that will overkill the app using this will be fine because the ui will be updated or recomposed when we get the whole new list of products from the firebase
        private set //makes the setter private that means we can not change the list in the ui

    init{
        fetchProducts()
    }
    private fun fetchProducts(){
        viewModelScope.launch{  //starts a coroutine tied to the viewModel-->prevents blocking the UI thread
            products=repository.getProducts()
        }
    }
}