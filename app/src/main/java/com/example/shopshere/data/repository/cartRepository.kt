package com.example.shopshere.data.repository

import com.example.shopshere.data.local.CartDao
import com.example.shopshere.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

class CartRepository(
    private val dao: CartDao
){
    val cartItems:Flow<List<CartItem>>=dao.getCartItems()
        get() {
            TODO()
        }

    suspend fun addItem(item: CartItem)=dao.insert(item)
    suspend fun updateItem(item: CartItem)=dao.update(item)
    suspend fun removeItem(item: CartItem)=dao.delete(item)
}