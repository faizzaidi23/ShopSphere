package com.example.shopshere.UserInterface.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogoutClick:()-> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title={Text("Profile")}
            )
        }
    ){internalPadding->
        LazyColumn(
            modifier=Modifier.padding(internalPadding).fillMaxSize()
        ){
            item{ProfileHeader()}

            item{Spacer(Modifier.height(16.dp))}

            item{ProfileOption(
                icon=Icons.Default.AddLocation,
                title="Saved addresses",
                onClick={}
            )}

            item{
                ProfileOption(
                    icon=Icons.Default.ShoppingCart,
                    title="WishList",
                    onClick = {}
                )
            }

            item{
                ProfileOption(
                    icon=Icons.Default.Settings,
                    title="Settings",
                    onClick = {}
                )
            }

            item{
                HorizontalDivider()

                ProfileOption(
                    icon = Icons.AutoMirrored.Filled.Logout,
                    title = "Logout",
                    onClick = onLogoutClick
                )
            }


        }
    }

}

@Composable
fun ProfileHeader(){
    Row(
       modifier=Modifier.fillMaxWidth().padding(20.dp),
        verticalAlignment=Alignment.CenterVertically
    ){
        Box(
            modifier=Modifier.size(70.dp).background(
                MaterialTheme.colorScheme.primary.copy(alpha=0.2f),
                CircleShape
            )
        )

        Spacer(Modifier.width(16.dp))

        Column{
            Text("Faiz Zaidi")//will be updated with the firebase user

            Text(
                "faizzaidi3105@gmail.com",
                style= MaterialTheme.typography.bodySmall
            )

        }
    }
}

@Composable
fun ProfileOption(
    icon: ImageVector,
    title:String,
    onClick:()-> Unit
){
    Row(
        modifier=Modifier.fillMaxWidth()
            .clickable{onClick()} // here we are calling the function directly 
            .padding(horizontal = 20.dp,vertical=14.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            icon, contentDescription = null
        )

        Spacer(Modifier.width(16.dp))
        Text(
            title,
            modifier=Modifier.weight(1f)
        )
        Icon(Icons.Default.ChevronRight, contentDescription = null)
    }
}