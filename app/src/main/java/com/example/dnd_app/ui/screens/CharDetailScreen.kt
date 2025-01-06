package com.example.dnd_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dnd_app.models.Race
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
        charDetailViewModel.setCharId(charId)
    }

    val viewState = charDetailViewModel.viewState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

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
                },
                actions = {
                    TextButton(onClick = {
                        navController?.navigate("EditCharScreen/${charId}")
                    }) {
                        Text(text = "Edit", color = MaterialTheme.colorScheme.primary) // Nastavení barvy textu
                    }
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
                    .padding(top = 16.dp)
                    .weight(1f),
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
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Titulek pro kartu rasy
                            Text(text = "Rasa", style = MaterialTheme.typography.bodyLarge)

                            // Základní informace o rase
                            Text(text = "Název: ${viewState.value.race?.Name}", style = MaterialTheme.typography.bodyMedium)

                            Text(text = "Subraces:", style = MaterialTheme.typography.bodySmall)
                            viewState.value.race?.Subrases?.forEach { subrace ->
                                Text(text = "- ${subrace.name}: ${subrace.popis}", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
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
            if (showDialog) {
                ConfirmDeleteDialog(
                    onConfirm = {
                        charDetailViewModel.deleteCharacter(charId)
                        navController?.navigateUp() // Návrat na předchozí obrazovku po smazání
                    },
                    onDismiss = { showDialog = false }
                )
            }

            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Delete Character")
            }
        }

    }
}


@Composable
fun ConfirmDeleteDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirm Delete") },
        text = { Text("Are you sure you want to delete this character?") },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
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