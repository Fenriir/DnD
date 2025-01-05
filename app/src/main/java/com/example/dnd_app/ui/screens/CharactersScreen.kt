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
import com.example.dnd_app.viewmodels.CharactersViewModel
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    navController: NavController?,
    viewModel: CharactersViewModel
) {
    val viewState = viewModel.viewState.collectAsState()
    val focusManager = LocalFocusManager.current
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
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                TextField(
                    modifier = Modifier.weight(1f).padding(vertical = 2.dp),
                    value = viewState.value.search,
                    label = {
                        Text("Search")
                    },
                    maxLines = 1,
                    onValueChange = {
                        viewModel.onSearchChange(it)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search,
                       // keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModel.searchCharacters()
                            focusManager.clearFocus()
                        }
                    )
                )
                IconButton(onClick = {
                    viewModel.searchCharacters()
                    focusManager.clearFocus()
                }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
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
                items(viewState.value.charactersList){ character ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp)
                            .clickable {
                                navController?.navigate("CharDetailScreen/${character.Name}")
                            }
                    ) {
                        Text(
                            text = character.Name,
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