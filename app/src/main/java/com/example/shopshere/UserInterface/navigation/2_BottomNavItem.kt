package com.example.shopshere.UserInterface.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route:String,
    val title:String,
    val icon: ImageVector
){
    object Home: BottomNavItem("home","Home",Icons.Default.Home)
    object Wishlist:BottomNavItem("wishlist","Wishlist",Icons.Default.FavoriteBorder)
    object Cart: BottomNavItem("cart","Cart",Icons.Default.ShoppingCart)
    //object Order: BottomNavItem("order","Order",Icons.Default.Reorder)
    object Search: BottomNavItem("search","Search",Icons.Default.Search)
    object Profile:BottomNavItem("profile","Profile",Icons.Default.Person)
}