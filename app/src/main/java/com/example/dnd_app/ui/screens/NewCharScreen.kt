package com.example.dnd_app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dnd_app.models.Characters
import com.example.dnd_app.models.HP
import com.example.dnd_app.models.Mana
import com.example.dnd_app.viewmodels.CharactersViewModel
import kotlin.random.Random
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCharScreen(
    navController: NavController?,
    viewModel: CharactersViewModel
) {
    var charId by remember { mutableStateOf("") }
    var charName by remember { mutableStateOf("") }
    var charLvl by remember { mutableStateOf("") }
    var charAktHp by remember { mutableStateOf("") }
    var charMaxHp by remember { mutableStateOf("") }
    var charDC by remember { mutableStateOf("") }
    var charRace by remember { mutableStateOf("") }
    var charAktMana by remember { mutableStateOf("") }
    var charMaxMana by remember { mutableStateOf("") }

    fun generateHexId(length: Int = 8): String {
        val hexChars = "0123456789ABCDEF"
        return (1..length)
            .map { hexChars[Random.nextInt(hexChars.length)] }
            .joinToString("")
    }
    var hexID = generateHexId()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Character") },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = Color.LightGray
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(8.dp)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                item {
                    TextField(
                        value = charId,
                        onValueChange = { charId = it },
                        label = { Text("Id") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    TextField(
                        value = charName,
                        onValueChange = { charName = it },
                        label = { Text("Name") }
                    )
                }
                item {
                    TextField(
                        value = charLvl,
                        onValueChange = { charLvl = it },
                        label = { Text("Lvl") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    TextField(
                        value = charAktHp,
                        onValueChange = { charAktHp = it },
                        label = { Text("Aktuální HP") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    TextField(
                        value = charMaxHp,
                        onValueChange = { charMaxHp = it },
                        label = { Text("Maximální HP") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    TextField(
                        value = charDC,
                        onValueChange = { charDC = it },
                        label = { Text("DC") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    TextField(
                        value = charRace,
                        onValueChange = { charRace = it },
                        label = { Text("RaceId") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    TextField(
                        value = charAktMana,
                        onValueChange = { charAktMana = it },
                        label = { Text("Aktuální mana") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    TextField(
                        value = charMaxMana,
                        onValueChange = { charMaxMana = it },
                        label = { Text("Maximální mana") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
                item{
                    Button(
                        onClick = {
                            // Vytvoření nového charakteru
                            val newCharacter = Characters(
                                Id = hexID,
                                CharId = charId,
                                Name = charName,
                                Lvl = charLvl.toInt(),
                                HP = HP(AktHp = charAktHp.toInt(), MaxHp = charMaxHp.toInt()),
                                DC = charDC.toInt(),
                                RaceId = charRace,
                                Mana = Mana(
                                    AktMana = charAktMana.toInt(),
                                    MaxMana = charMaxMana.toInt()
                                )
                            )
                            viewModel.createCharacter(newCharacter)
                            navController?.navigateUp() // Návrat na předchozí obrazovku
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Create Character")
                    }
                }
            }

        }

    }
}