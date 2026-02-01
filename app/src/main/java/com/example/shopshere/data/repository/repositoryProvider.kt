package com.example.shopshere.data.repository

import android.content.Context
import com.example.shopshere.data.local.CartDatabase


/*

object RepositoryProvider
A single place that creates and shares one CartRepository instance across the app
*/

object RepositoryProvider{
    private var cartRepository: CartRepository?=null

    fun provideCartRepository(context:Context): CartRepository{
        return cartRepository?:synchronized(this){
            val dao= CartDatabase.getDatabase(context).cartDao()
            CartRepository(dao).also{
                cartRepository=it
            }
        }
    }
}