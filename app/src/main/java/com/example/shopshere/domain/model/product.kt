package com.example.shopshere.domain.model

data class Product(
    val id:String="",
    val title:String="",
    val price:Int=0,
    val description:String="",
    val imageUrls:List<String> =emptyList(),
    val stock:Int=0,
    val sellerId:String=""
)