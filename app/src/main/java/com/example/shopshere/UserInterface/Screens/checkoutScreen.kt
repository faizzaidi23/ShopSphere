package com.example.shopshere.UserInterface.Screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.theme.Card
import com.example.shopshere.UserInterface.theme.Divider
import com.example.shopshere.UserInterface.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen() {
    val subtotal = 2397
    val shipping = 0
    val total = subtotal + shipping

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Checkout") })
        },
        bottomBar = {
            PlaceOrderBar(total)
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item { AddressSection() }

            item { PaymentSection() }

            item { OrderSummary(subtotal, shipping, total) }

            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

@Composable
fun AddressSection() {

    Card(shape = RoundedCornerShape(14.dp)) {

        Row(
            modifier=Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(Icons.Default.LocationOn,null)
                Spacer(Modifier.width(8.dp))

                Column {
                    Text("Deliver To")
                    Text(
                        "Faiz Zaidi\n221B Baker Street\nNew Delhi, 110001",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            TextButton(onClick = {}) {
                Text("Change")
            }
        }
    }
}

@Composable
fun PaymentSection() {

    var selected by remember {mutableStateOf("UPI") }
    Card(shape = RoundedCornerShape(14.dp)) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Payment, null)
                Spacer(Modifier.width(8.dp))
                Text("Payment Method")
            }

            Spacer(Modifier.height(12.dp))

            listOf("UPI", "Card", "Cash on Delivery").forEach { method ->

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selected == method,
                        onClick = { selected = method }
                    )
                    Text(method)
                }
            }
        }
    }
}

@Composable
fun OrderSummary(
    subtotal: Int,
    shipping: Int,
    total: Int
) {

    Card(shape = RoundedCornerShape(14.dp)) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text("Order Summary")

            Spacer(Modifier.height(8.dp))

            SummaryRow("Subtotal", subtotal)
            SummaryRow("Shipping", shipping)

            Divider(Modifier.padding(vertical = 8.dp))

            SummaryRow("Total", total, isBold = true)
        }
    }
}

@Composable
fun SummaryRow(
    label: String,
    price: Int,
    isBold: Boolean = false
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label)

        Text(
            "₹$price",
            style = if (isBold)
                MaterialTheme.typography.titleMedium
            else
                MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun PlaceOrderBar(total: Int) {

    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Primary)
    ) {
        Text("Place Order • ₹$total")
    }
}