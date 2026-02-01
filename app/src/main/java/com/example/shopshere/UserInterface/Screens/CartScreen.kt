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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(){

    //temporary UI list, hardcoded for the ui purpose
    val cartItems=remember{mutableStateListOf("Women printed kurta","Casual T shirts","Blue jeans")}

    val quantities=remember{mutableStateListOf(1, 2, 3)}
    val pricePerItem=800

    val total=quantities.sum()*pricePerItem

    Scaffold(
        topBar = {
            TopAppBar(
                title={Text("My Cart")}
            )
        },
        bottomBar = {CheckoutBar(total)}
    ){internalPadding->
        LazyColumn(
            modifier=Modifier.padding(internalPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(cartItems.size){i->
                CartItemCard(
                    title =cartItems[i],
                    quantity = quantities[i],
                    price =pricePerItem,
                    onIncrease = {quantities[i]+=1},
                    onDecrease = {
                        if(quantities[i] >= 1){
                            quantities[i] = quantities[i] - 1
                        }
                    },
                    onRemove = {
                        cartItems.removeAt(i)
                        quantities.removeAt(i)
                    }

                )
            }

            item{
                PriceSummary(total)
            }
            item {
                Spacer(Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun CartItemCard(
    title: String,
    quantity: Int,
    price: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
){
    Card(shape= RoundedCornerShape(14.dp)){
        Row(
            modifier=Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            //Image Placeholder
            Box(
                modifier=Modifier.size(80.dp).background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier=Modifier.weight(1f)){
                Text(title)
                Spacer(Modifier.height(6.dp))
                Text("₹$price")
            }

            QuantityStepper(
                quantity = quantity,
                onIncrease = onIncrease,
                onDecrease = onDecrease
            )

            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, null)
            }

        }
    }
}
@Composable
fun QuantityStepper(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {

    Row(verticalAlignment =Alignment.CenterVertically) {
        IconButton(onClick = onDecrease) {
            Icon(Icons.Default.Remove, null)
        }
        Text(quantity.toString())
        IconButton(onClick =onIncrease) {
            Icon(Icons.Default.Add, null)
        }
    }
}

@Composable
fun PriceSummary(total: Int) {
    Card {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text("Subtotal: ₹$total")
            Text("Shipping:Free")
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Total: ₹$total",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun CheckoutBar(total: Int) {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Primary)
    ) {
        Text("Checkout • ₹$total")
    }
}
