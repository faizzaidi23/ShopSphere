package com.example.shopshere.UserInterface.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopshere.UserInterface.Components.CategorySection
import com.example.shopshere.UserInterface.Components.DealOfTheDay
import com.example.shopshere.UserInterface.Components.HomeTopBar
import com.example.shopshere.UserInterface.Components.OfferBanner
import com.example.shopshere.UserInterface.Components.ProductSection
import com.example.shopshere.UserInterface.Components.SearchhBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProductClick:()-> Unit
){
    LazyColumn(
        modifier=Modifier.fillMaxSize(),
        contentPadding= PaddingValues(bottom=80.dp)
    ){
        item{Spacer(Modifier.height(8.dp))}
        item{HomeTopBar()}
        item{Spacer(Modifier.height(8.dp))}
        item{SearchhBar()}
        item{Spacer(Modifier.height(12.dp))}
        item{CategorySection()}

        item{OfferBanner()}
        item{Spacer(Modifier.height(12.dp))}
        item{DealOfTheDay()}
        item{Spacer(Modifier.height(12.dp))}
        item{ProductSection(title="Trending Products",onProductClick=onProductClick)}

    }
}