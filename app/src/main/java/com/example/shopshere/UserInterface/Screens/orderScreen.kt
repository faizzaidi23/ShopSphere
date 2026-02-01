package com.example.shopshere.UserInterface.Screens

import android.R.attr.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun OrderScreen(){
    val orders=listOf(
        OrderUi("ORD-10234", "25 Jan 2026", 2397, "Delivered"),
        OrderUi("ORD-10235", "27 Jan 2026", 1599, "Shipped"),
        OrderUi("ORD-10236", "29 Jan 2026", 899, "Processing")
    )

    Scaffold (
        topBar = { TopAppBar(
            title={Text("My orders")}
        ) }
    ){ padding->
        if(orders.isEmpty()){
            EmptyOrders()
            return@Scaffold
        }

        LazyColumn(
            modifier=Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(orders){
                order->
                OrderCard(order)
            }
        }
    }
}

/*
simple ui data class for the ui purpose only
*/

private data class OrderUi(
    val id: String,
    val date: String,
    val total: Int,
    val status: String
)


@Composable
fun EmptyOrders(){
    Box(
        modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("No orders yet 📦")
    }
}


@Composable
fun OrderCard(order: OrderUi){

}