package com.example.shopshere.UserInterface.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.theme.Card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(){
    val wishListItems=remember{mutableStateListOf(
        "Women printed kurta",
        "Casual T shirt",
        "blue denim jeans",
        "sneakers"
    )}

    Scaffold(
        topBar = {
            TopAppBar(
                title={Text("WishList")}
            )
        }
    ){internalPadding->

        if(wishListItems.isEmpty()){
            EmptyWishList()
            return@Scaffold
        }
        LazyVerticalGrid(
            columns=GridCells.Fixed(2),
            modifier=Modifier.padding(internalPadding).fillMaxSize(),
            contentPadding=PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(wishListItems.size,key={it}){
                index->
                WishlistCard(
                    title=wishListItems[index],
                    price=1000,
                    onRemove = {wishListItems.remove(wishListItems[index])},
                    onMoveToCart = {}

                )

            }
        }

    }
}

@Composable
fun EmptyWishList(){
    Box(modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text="Your wishlist is empty"
        )
    }
}

@Composable
fun WishlistCard(
    title:String,
    price: Int,
    onRemove:()->Unit,
    onMoveToCart:()-> Unit
){
    Card(
        shape=RoundedCornerShape(14.dp)
    ){
        Column(
            modifier=Modifier.padding(10.dp)
        ){

            //Image will come here later
            Box(
                modifier=Modifier.height(110.dp)
                    .fillMaxWidth().background(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(Modifier.height(8.dp))
            Text(text=title,
                maxLines=2
            )
            Spacer(Modifier.height(4.dp))

            Text(
                text="$price",
                style=MaterialTheme.typography.titleSmall
            )

            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier=Modifier.fillMaxWidth()
            ){
                TextButton(onClick = onMoveToCart) {
                    Text("Move to Cart")
                }

                IconButton(onClick = onRemove) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }

            }
        }
    }
}