package com.example.shopshere.UserInterface.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.theme.Primary
import com.example.shopshere.data.repository.RepositoryProvider
import com.example.shopshere.domain.model.CartItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProductDetailScreen(
    onBack:()-> Unit={}
){

    //newly added
    val context=LocalContext.current



    Scaffold(
        topBar = {
            TopAppBar(
                title={},
                navigationIcon = {
                    IconButton(onClick = onBack){
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = null)
                    }
                },
                actions={
                    IconButton(onClick = {}){
                        Icon(Icons.Default.FavoriteBorder, contentDescription = null)
                    }
                }
            )

        },
        bottomBar = {
            BottomActionBar(context = context)
        }
    ){internalPadding->
        LazyColumn(
            modifier=Modifier.padding(internalPadding).fillMaxSize()
        ){
            item{ProductImageSection()}
            item{Spacer(Modifier.height(8.dp))}

            item{ProductInfoSection()}
            item{Spacer(Modifier.height(12.dp))}

            item{SizeSelector()}
            item{Spacer(Modifier.height(8.dp))}

            item{DescriptionSection()}
            item{Spacer(Modifier.height(12.dp))}

        }

    }

}

@Composable
fun ProductImageSection(){
    Box(
        modifier=Modifier.fillMaxWidth()
            .height(280.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}

@Composable
fun ProductInfoSection() {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "Women Printed Kurta",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(6.dp))
        Text("⭐ 4.5 (56,890 reviews)")
        Spacer(Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                "₹1500",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.width(8.dp))

            Text(
                "₹2499",
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(Modifier.width(8.dp))
            AssistChip(
                onClick = {},
                label = { Text("40% OFF") }
            )
        }
    }
}

@Composable
fun SizeSelector() {
    val sizes = listOf("S", "M", "L", "XL", "XXL")
    var selected by remember { mutableStateOf("M") }
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text("Select Size")
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            sizes.forEach { size ->
                FilterChip(
                    selected = selected == size,
                    onClick = { selected = size },
                    label = { Text(size) },
                    shape = CircleShape
                )
            }
        }
    }
}


@Composable
fun DescriptionSection() {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Product Details", style =MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text(
            "Premium cotton fabric with comfortable fit. "
                    + "Perfect for daily wear and casual outings. "
                    + "Soft, breathable and stylish."
        )
    }
}


@Composable
fun BottomActionBar(context:android.content.Context) {
    val repository= RepositoryProvider.provideCartRepository(context = context)
    val scope= rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedButton(
            onClick = {
                scope.launch{
                    repository.addItem(
                        CartItem(
                            id = "1",
                            title="Women Printed Kurta",
                            price=1500,
                            quantity = 1
                        )
                    )
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Text("Add to Cart")
        }
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Primary)
        ) {
            Text("Buy Now")
        }
    }
}



