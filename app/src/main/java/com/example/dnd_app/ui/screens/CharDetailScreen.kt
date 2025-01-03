package com.example.dnd_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.dnd_app.ui.components.NavBackButton
import com.example.dnd_app.viewmodels.CharDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharDetailScreen(
    charDetailViewModel: CharDetailViewModel,
    navController: NavController?,
    charId: String
){
    LaunchedEffect(charId){
        charDetailViewModel.initLoad(charId)
    }

    val viewState = charDetailViewModel.viewState.collectAsState()
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Char detail")
                },
                navigationIcon = {
                    NavBackButton (
                        onClick = {
                            navController?.popBackStack()
                        }
                    )
                }
            )
        }
    ){ innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ){
            Text("id: $charId")
        }

    }
}

@Composable
@Preview
fun CharDetailScreenPreview(){
    CharDetailScreen(CharDetailViewModel(),null,"1")
}