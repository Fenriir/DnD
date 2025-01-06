package com.example.dnd_app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dnd_app.ui.screens.CharDetailScreen
import com.example.dnd_app.viewmodels.CharactersViewModel
import com.example.dnd_app.ui.screens.CharactersScreen
import com.example.dnd_app.ui.screens.EditCharScreen
import com.example.dnd_app.ui.screens.NewCharScreen
import com.example.dnd_app.viewmodels.CharDetailViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    charactersViewModel: CharactersViewModel,
    charDetailViewModel: CharDetailViewModel
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

        composable("CharDetailScreen/{charId}"){ backStackEntry ->
            val charId = backStackEntry.arguments?.getString("charId")?:""
            CharDetailScreen(charDetailViewModel,navController, charId)
        }

        composable("NewCharScreen") {
            NewCharScreen(
                navController = navController,
                viewModel = charactersViewModel)
        }

        composable("EditCharScreen/{charId}") {backStackEntry ->
            val charId = backStackEntry.arguments?.getString("charId")?:""
            EditCharScreen(
                navController = navController,
                charDetailViewModel = charDetailViewModel,
                charId)
        }
    }


}