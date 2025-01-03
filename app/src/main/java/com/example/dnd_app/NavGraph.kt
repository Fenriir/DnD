package com.example.dnd_app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dnd_app.viewmodels.CharactersViewModel
import com.example.dnd_app.ui.screens.CharactersScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    charactersViewModel: CharactersViewModel
){
    NavHost(
        navController = navController,
        startDestination = "CharactersScreen"
    ){
        composable("CharactersScreen"){
            CharactersScreen(
                navController = navController,
                viewModel = charactersViewModel)
        }
    }
}