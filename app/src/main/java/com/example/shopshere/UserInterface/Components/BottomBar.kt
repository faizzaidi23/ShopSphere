package com.example.shopshere.UserInterface.Components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.shopshere.UserInterface.navigation.BottomNavItem
import com.example.shopshere.UserInterface.theme.Primary

@Composable
fun BottomBar(navController: NavController){
    val items=listOf(
        BottomNavItem.Home,
        BottomNavItem.Wishlist,
        BottomNavItem.Cart,
        BottomNavItem.Search,
        BottomNavItem.Profile
    )

    NavigationBar(containerColor=Color.White){
        items.forEach {item->
            NavigationBarItem(
                selected=false, //will be changed later
                onClick = {
                    navController.navigate(item.route){
                        popUpTo("home")
                        launchSingleTop=true
                    }
                },
                icon = {Icon(item.icon, contentDescription = item.title)},
                label={Text(item.title)},
                colors= NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    selectedTextColor=Primary,
                    indicatorColor=Color.Transparent
                )
            )
        }
    }
}