package com.example.shopshere.UserInterface.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shopshere.R

@Composable
fun HomeTopBar(){
    Row(
        modifier=Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
      Text(text="ShopSphere",style= MaterialTheme.typography.titleLarge)

      Image(
          painter=painterResource(id=R.drawable.ic_profile),
          contentDescription = "Profile",
          modifier=Modifier.size(36.dp).clip(CircleShape)
      )
    }
}