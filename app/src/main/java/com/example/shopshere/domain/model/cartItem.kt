package com.example.shopshere.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(

    @PrimaryKey
    val id: String,

    val title:String,
    val price:Int,
    val quantity: Int
)