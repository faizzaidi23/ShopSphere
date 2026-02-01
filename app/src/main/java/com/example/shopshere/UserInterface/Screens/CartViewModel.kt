package com.example.shopshere.UserInterface.Screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopshere.data.repository.CartRepository
import com.example.shopshere.domain.model.CartItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CartRepository
):ViewModel(){

    val cartItems=repository.cartItems.
        stateIn(viewModelScope, SharingStarted.Lazily,emptyList())

    fun increase(item: CartItem){
        viewModelScope.launch{
            repository.updateItem(item.copy(quantity = item.quantity+1))
        }
    }

    fun decrease(item: CartItem){
        if(item.quantity<=1)return

        viewModelScope.launch{
            repository.updateItem(item.copy(quantity = item.quantity-1))
        }
    }

    fun remove(item: CartItem){
        viewModelScope.launch {
            repository.removeItem(item)
        }
    }
}