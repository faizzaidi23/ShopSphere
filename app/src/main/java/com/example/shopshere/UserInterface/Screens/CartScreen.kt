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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopshere.UserInterface.theme.Primary
import com.example.shopshere.data.local.CartDatabase
import com.example.shopshere.data.repository.CartRepository
import com.example.shopshere.data.repository.RepositoryProvider
import com.example.shopshere.domain.model.CartItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(){

    val context=LocalContext.current

    //val dao= CartDatabase.getDatabase(context).cartDao()
    //val repository= CartRepository(dao)

    val repository= RepositoryProvider.provideCartRepository(context)

    //to manage the quantities now dynamically
    val viewModel: CartViewModel=viewModel(factory = CartViewModelFactory(repository))

    //temporary UI list, hardcoded for the ui purpose
    //val cartItems=remember{mutableStateListOf("Women printed kurta","Casual T shirts","Blue jeans")}

    //new list collecting from the flow
    val cartItems by viewModel.cartItems.collectAsState()


    //before
    //val quantities=remember{mutableStateListOf(1, 2, 3)}
    //val pricePerItem=800

    val total=cartItems.sumOf{it.price*it.quantity}

    Scaffold(
        topBar = {
            TopAppBar(
                title={Text("My Cart")}
            )
        },
        bottomBar = {CheckoutBar(total)}
    ){internalPadding->

        //new:empty state..before the list always had fake items
        if(cartItems.isEmpty()){
            EmptyCart()
            return@Scaffold
        }

        LazyColumn(
            modifier=Modifier.padding(internalPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(cartItems, key={it.id}){item->
                CartItemCard(
//                    title =cartItems[i],
//                    quantity = quantities[i],
//                    price =pricePerItem,
//                    onIncrease = {quantities[i]+=1},
//                    onDecrease = {
//                        if(quantities[i] >= 1){
//                            quantities[i] = quantities[i] - 1
//                        }
//                    },
//                    onRemove = {
//                        cartItems.removeAt(i)
//                        quantities.removeAt(i)
//                    }

                    item=item,
                    onIncrease = {viewModel.increase(item)},
                    onDecrease = {viewModel.decrease(item)},
                    onRemove = {viewModel.remove(item)}

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
fun EmptyCart() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(16.dp))
            Text(
                "Your cart is empty",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Add items to get started",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
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
                Text(item.title)
                Spacer(Modifier.height(6.dp))
                Text("₹${item.price}")
            }

            QuantityStepper(
                quantity = item.quantity,
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
