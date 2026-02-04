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
import coil.compose.AsyncImage
import com.example.shopshere.UserInterface.theme.Surface
import com.example.shopshere.domain.model.Product

@Composable
fun ProductSection(
    title: String,
    products:List<Product>,//This is added when I was done with the Product viewModel
    onProductClick:(String)-> Unit
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
            /*
            This was hardcoded before when there was no product  backend on the firebase
            items(6) {
                ProductCard(
                    onClick = onProductClick
                )
            }
            */
            items(products.size){
                index->
                ProductCard(
                    product=products[index],
                    onClick={onProductClick(products[index].id)}
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,//This was added later when the viewModel part was done and the firebase firestore of the products
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
                /*

                This code was before when there was no viewModel and no images
                Surface(
                    modifier=Modifier.height(120.dp).fillMaxWidth(),
                    color= MaterialTheme.colorScheme.surfaceVariant,
                    shape= MaterialTheme.shapes.small
                ){

                }

                */

                AsyncImage(
                    model = product.imageUrls.firstOrNull(),
                    contentDescription = null,
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                )

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
                text=product.title,
                maxLines=2,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            /*

            Old code before the productViewModel part

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
            */

            Text(
                "${product.price}",
                style= MaterialTheme.typography.titleSmall
            )
        }
    }
}