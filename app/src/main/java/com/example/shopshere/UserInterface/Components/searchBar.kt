package com.example.shopshere.UserInterface.Components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchhBar(){
    OutlinedTextField(
        value="",
        onValueChange = {},
        modifier=Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        placeholder = {Text("Search any product")},
        leadingIcon = {Icon(Icons.Default.Search, contentDescription = null)},
        shape= RoundedCornerShape(16.dp),
        singleLine = true
    )
}