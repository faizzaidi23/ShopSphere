package com.example.shopshere.UserInterface.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.theme.Surface

@Composable
fun ProductSection(
    title: String,
    onProductClick:()-> Unit
) {

    Column {
        Row(
            modifier=Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text=title,
                style= MaterialTheme.typography.titleMedium
            )

            TextButton(onClick = {}){
                Text("View all")
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(6) {
                ProductCard(
                    onClick = onProductClick
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    onClick:()-> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { onClick() },
        shape=MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Box{
                Surface(
                    modifier=Modifier.height(120.dp).fillMaxWidth(),
                    color= MaterialTheme.colorScheme.surfaceVariant,
                    shape= MaterialTheme.shapes.small
                ){

                }

                AssistChip(
                    onClick = {},
                    label={Text("40% Off")},
                    modifier=Modifier.padding(6.dp).align(Alignment.TopStart)
                )

                IconButton(
                    onClick = {},
                    modifier=Modifier.align(Alignment.TopEnd)
                ){
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "WishList"
                    )
                }
            }

            Spacer(modifier=Modifier.height(8.dp))

            Text(
                "Women Printed Kurta",
                maxLines=2,
                style=MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier=Modifier.height(4.dp))

            Text(
                "⭐ 4.5 (56890)",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Text(
                    "₹1500",
                    style=MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "₹2499",
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            }
        }
    }
}