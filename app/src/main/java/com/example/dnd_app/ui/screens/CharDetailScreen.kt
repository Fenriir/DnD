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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dnd_app.ui.components.NavBackButton
import com.example.dnd_app.ui.theme.Typography
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = viewState.value.character?.Name ?: "Character detail")
                },
                navigationIcon = {
                    NavBackButton(
                        onClick = {
                            navController?.popBackStack()
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 6.dp)
                    .padding(14.dp)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    CharacterInfoCard(
                        label = "Lvl: ",
                        value = viewState.value.character?.Lvl?.toString() ?: "0")
                }
                item {
                    CharacterInfoCard(
                        label = "DC: ",
                        value = viewState.value.character?.DC?.toString() ?: "8")
                }
                item {
                    CharacterInfoCard(
                        label = "Rasa: ",
                        value = viewState.value.character?.Rasa ?: "neznámá")
                }
                item {
                    CharacterHpCard(
                        currentHp = viewState.value.character?.HP?.AktHp ?: 0,
                        maxHp = viewState.value.character?.HP?.MaxHp ?: 0
                    )
                }
                item {
                    CharacterManaCard(
                        currentHp = viewState.value.character?.Mana?.AktMana ?: 0,
                        maxHp = viewState.value.character?.Mana?.MaxMana ?: 0
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterHpCard(currentHp: Int, maxHp: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Mezera mezi položkami
        ) {
            Text(text = "Aktuální HP: $currentHp", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Maximální HP: $maxHp", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun CharacterManaCard(currentHp: Int, maxHp: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Mezera mezi položkami
        ) {
            Text(text = "Aktuální Mana: $currentHp", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Maximální Mana: $maxHp", style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Composable
fun CharacterInfoCard(label: String, value: String) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
@Preview
fun CharDetailScreenPreview(){
    CharDetailScreen(CharDetailViewModel(),null,"1")
}