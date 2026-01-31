package com.example.shopshere.UserInterface.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopshere.UserInterface.Components.BottomBar
import com.example.shopshere.UserInterface.Screens.CartScreen
import com.example.shopshere.UserInterface.Screens.HomeScreen
import com.example.shopshere.UserInterface.Screens.ProductDetailScreen
import com.example.shopshere.UserInterface.Screens.ProfileScreen
import com.example.shopshere.UserInterface.Screens.WishlistScreen

@Composable
fun AppNavGraph(){
    val navController= rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ){
            composable(BottomNavItem.Home.route){
                HomeScreen()
            }
            composable(BottomNavItem.Search.route){
                ProfileScreen()
            }
            composable(BottomNavItem.Cart.route){
                CartScreen()
            }
            composable(BottomNavItem.Wishlist.route){
                WishlistScreen()
            }
            composable("profile"){
                ProfileScreen()
            }

            composable("details"){
                ProductDetailScreen(
                    onBack = {navController.popBackStack()}
                )
            }
        }
    }
}