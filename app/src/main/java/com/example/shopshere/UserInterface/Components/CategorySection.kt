package com.example.shopshere.UserInterface.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CategorySection(){
    val categories=listOf("Beauty","Fashion","Kids","Men's","Women's")

    LazyRow(
        modifier=Modifier.padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(categories.size){index->
            CategoryChip(categories[index])
        }
    }
}

@Composable
fun CategoryChip(name: String){
    AssistChip(
        onClick = {},
        label={Text(name)}
    )
}