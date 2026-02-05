package com.example.shopshere.UserInterface.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopshere.UserInterface.Components.BottomBar
import com.example.shopshere.UserInterface.Screens.CartScreen
import com.example.shopshere.UserInterface.Screens.HomeScreen
import com.example.shopshere.UserInterface.Screens.LoginScreen
import com.example.shopshere.UserInterface.Screens.OrderScreen
import com.example.shopshere.UserInterface.Screens.ProductDetailScreen
import com.example.shopshere.UserInterface.Screens.ProfileScreen
import com.example.shopshere.UserInterface.Screens.RegisterScreen
import com.example.shopshere.UserInterface.Screens.SearchScreen
import com.example.shopshere.UserInterface.Screens.WishlistScreen
import com.example.shopshere.data.repository.AuthRepository

@Composable
fun AppNavGraph(){
    val navController= rememberNavController()

    val repo=remember{ AuthRepository() }

    val startDestination=if(repo.currentUser!=null)"home"
    else "login"

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ){
            composable(BottomNavItem.Home.route){
                HomeScreen(onProductClick = { productId ->
                    navController.navigate("details/$productId")
                })
            }
            composable(BottomNavItem.Search.route){
                SearchScreen()
            }
            composable(BottomNavItem.Cart.route){
                CartScreen()
            }
            composable(BottomNavItem.Wishlist.route){
                WishlistScreen()
            }
            composable(
                BottomNavItem.Profile.route
            ){
                ProfileScreen(
//                    onOrderClick = {navController.navigate("order")},
                    onLogoutClick = {}
                )
            }

            composable("details/{productId}"){
                ProductDetailScreen(
                    onBack = {navController.popBackStack()}
                )
            }

            composable("order"){
                OrderScreen()
            }

            composable("login"){
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate("home"){
                            popUpTo("login"){inclusive=true}
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate("register")
                    }
                )
            }


            composable("register"){
                RegisterScreen(
                    onBackToLogin = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}