package com.example.shopshere.UserInterface.Screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import com.example.shopshere.data.repository.AuthRepository

@Composable
fun LoginScreen(
    onLoginSuccess:()->Unit
){

    val repo=remember{ AuthRepository() }
    val scope= rememberCoroutineScope()

    var name by remember{mutableStateOf("")}

    var email by remember{mutableStateOf("")}

    var role by remember{mutableStateOf("buyer")}

}