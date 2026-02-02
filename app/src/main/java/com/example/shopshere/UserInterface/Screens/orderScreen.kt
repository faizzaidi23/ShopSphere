package com.example.shopshere.UserInterface.Screens

import android.R.attr.order
import android.R.attr.padding
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.theme.Card


@OptIn(ExperimentalMaterial3Api::class)
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
            items(orders.size){
                index->
                OrderCard(orders[index])
            }
        }
    }
}

/*
simple ui data class for the ui purpose only
*/

 data class OrderUi(
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
    Card(
        shape= RoundedCornerShape(14.dp)
    ){
        Column(
            modifier=Modifier.padding(16.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier=Modifier.fillMaxWidth()
            ){
                Text(
                    text="Order Id:${order.id}",
                    style= MaterialTheme.typography.bodyMedium
                )

                Text(
                    text=order.status,
                    color= MaterialTheme.colorScheme.primary
                )
            }

            Spacer(Modifier.height(6.dp))

            Text("Date:${order.date}")

            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Default.LocalShipping,
                    contentDescription = null
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    text = "Total: ₹${order.total}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

