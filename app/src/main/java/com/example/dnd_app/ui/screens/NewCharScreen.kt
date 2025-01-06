package com.example.dnd_app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dnd_app.viewmodels.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCharScreen(
    navController: NavController?,
    viewModel: CharactersViewModel
) {
    // Můžete si vytvořit proměnné pro uchování hodnot charakteru
    var charName = remember { mutableStateOf("") }
    var charLvl = remember { mutableStateOf("") }
    var charAktHp = remember { mutableStateOf("") }
    var charMaxHp = remember { mutableStateOf("") }
    var charMaxMana = remember { mutableStateOf("") }
    var charAktMana = remember { mutableStateOf("") }
    var charDC = remember { mutableStateOf("") }
    var charRace = remember { mutableStateOf("") }

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
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = charName.value,
                onValueChange = { charName.value = it },
                label = { Text("Character Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = charLvl.value,
                onValueChange = { charLvl.value = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Button(
                onClick = {
                    // Zde  implementovat logiku pro uložení nového charakteru
//                    viewModel.addCharacter(charName.value, charLvl.value)
                    navController?.navigateUp() // Návrat na předchozí obrazovku
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Character")
            }
        }
    }
}