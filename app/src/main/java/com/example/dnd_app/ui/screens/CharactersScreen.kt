package com.example.dnd_app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dnd_app.viewmodels.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    navController: NavController?,
    viewModel: CharactersViewModel
) {
    val viewState = viewModel.viewState.collectAsState()
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Char"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = Color.LightGray
                )
            )
        },
        bottomBar = {

        }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ){
            Text("statický text")
            LazyColumn (
                modifier = Modifier.fillMaxSize().weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                item{
                    Text(
                        text = "neco",
                        style = MaterialTheme.typography.titleMedium

                    )
                }
                items(viewState.value.charactersNamesLists){ charName ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp)
                            .clickable {
                                navController?.navigate("CharDetailScreen/$charName")
                            }
                    ) {
                        Text(
                            text = charName,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }
            }
            Text("statický text", modifier = Modifier.fillMaxSize().weight(1f))

        }
    }
}

@Composable
@Preview
fun CharactersScreenPreview(){
    CharactersScreen( navController = null,
        viewModel = CharactersViewModel())
}