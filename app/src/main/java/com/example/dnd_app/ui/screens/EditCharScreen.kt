package com.example.dnd_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dnd_app.viewmodels.EditCharViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCharScreen(
    navController: NavController?,
    vewModel: EditCharViewModel,
    charId: String
) {
    LaunchedEffect(charId) {
        vewModel.setCharId(charId)
    }

    val viewState = vewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = viewState.value.character?.Name ?: "Character detail")
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = Color.LightGray
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp).padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = viewState.value.character?.Name ?: "",
                onValueChange = { vewModel.updateCharacterName(it) },
                label = { Text("Name") }
            )
            TextField(
                value = viewState.value.character?.Lvl?.toString() ?: "0",
                onValueChange = { vewModel.updateCharacterLevel(it.toIntOrNull() ?: 0) },
                label = { Text("Level") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = viewState.value.character?.DC?.toString() ?: "0",
                onValueChange = { vewModel.updateCharacterDC(it.toIntOrNull() ?: 0) },
                label = { Text("DC") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = viewState.value.character?.HP?.AktHp?.toString() ?: "0",
                onValueChange = { newValue ->
                    val newAktHp = newValue.toIntOrNull() ?: 0
                    vewModel.updateCharacterHp(
                        newAktHp,
                        viewState.value.character?.HP?.MaxHp ?: 0
                    )
                },
                label = { Text("Current HP") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            TextField(
                value = viewState.value.character?.HP?.MaxHp?.toString() ?: "0",
                onValueChange = { newValue ->
                    val newMaxHp = newValue.toIntOrNull() ?: 0
                    vewModel.updateCharacterHp(
                        viewState.value.character?.HP?.AktHp ?: 0,
                        newMaxHp
                    )
                },
                label = { Text("Max HP") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

// Podobně pro aktuální a maximální manu
            TextField(
                value = viewState.value.character?.Mana?.AktMana?.toString() ?: "0",
                onValueChange = { newValue ->
                    val newAktMana = newValue.toIntOrNull() ?: 0
                    vewModel.updateCharacterMana(
                        newAktMana,
                        viewState.value.character?.Mana?.MaxMana ?: 0
                    )
                },
                label = { Text("Current Mana") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            TextField(
                value = viewState.value.character?.Mana?.MaxMana?.toString() ?: "0",
                onValueChange = { newValue ->
                    val newMaxMana = newValue.toIntOrNull() ?: 0
                    vewModel.updateCharacterMana(
                        viewState.value.character?.Mana?.AktMana ?: 0, newMaxMana
                    )
                },
                label = { Text("Max Mana") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = {
                    vewModel.updateCharacter(charId)
                    navController?.navigateUp()
                    navController?.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Changes")
            }


        }
    }
}
