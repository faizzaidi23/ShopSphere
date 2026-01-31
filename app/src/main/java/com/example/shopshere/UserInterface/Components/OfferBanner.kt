package com.example.shopshere.UserInterface.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.theme.Card
import com.example.shopshere.UserInterface.theme.Surface
import com.example.shopshere.UserInterface.theme.pinkPrimary

@Composable
fun OfferBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = pinkPrimary)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("50–40% OFF", color = Surface, style = MaterialTheme.typography.titleLarge)
            Text("Now in product\nAll colours", color = Surface)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {}) {
                Text("Shop Now")
            }
        }
    }
}