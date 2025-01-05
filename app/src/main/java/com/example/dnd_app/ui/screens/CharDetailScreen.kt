package com.example.dnd_app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dnd_app.ui.components.NavBackButton
import com.example.dnd_app.viewmodels.CharDetailViewModel
import com.example.dnd_app.viewmodels.CharactersViewModel
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharDetailScreen(
    charDetailViewModel: CharDetailViewModel,
    navController: NavController?,
    charId: String
){
    LaunchedEffect(charId){
        charDetailViewModel.setCharId(charId)
    }

    val viewState = charDetailViewModel.viewState.collectAsState()
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = viewState.value.character?.Name ?:"Character detail")
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
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .padding(innerPadding)
//        ){
//            Text("id: $charId")
//        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
        ){}
        LazyColumn (
            modifier =  Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            item{
                Text(
                    text = "Postavy",
                    style = MaterialTheme.typography.titleMedium
                )
            }

        }
    }
}

@Composable
@Preview
fun CharDetailScreenPreview(){
    CharDetailScreen(CharDetailViewModel(),null,"1")
}