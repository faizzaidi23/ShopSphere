package com.example.shopshere.UserInterface.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.theme.Card
import com.example.shopshere.UserInterface.theme.Primary

@Composable
fun DealOfTheDay() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Primary)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Deal of the Day", color = MaterialTheme.colorScheme.onPrimary)
                Text("22h 55m remaining", color = MaterialTheme.colorScheme.onPrimary)
            }
            TextButton(onClick = {}) {
                Text("View all", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}