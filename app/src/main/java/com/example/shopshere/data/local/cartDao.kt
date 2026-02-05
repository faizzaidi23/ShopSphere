package com.example.shopshere.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shopshere.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao{

    @Query("Select * from cart_item")
    fun getCartItems():Flow<List<CartItem>>

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItem)

    @Update
    suspend fun update(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)

    @Query("Delete from cart_item")
    suspend fun clearCart()
}