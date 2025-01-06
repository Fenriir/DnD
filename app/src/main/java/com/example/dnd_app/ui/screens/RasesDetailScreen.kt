package com.example.dnd_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dnd_app.ui.components.NavBackButton
import com.example.dnd_app.viewmodels.RasesDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RaceDetailScreen(
    raceDetailViewModel: RasesDetailViewModel,
    navController: NavController?,
    raceId: String
){
    LaunchedEffect(raceId){
        raceDetailViewModel.setRaceId(raceId)
    }

    val viewState = raceDetailViewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = viewState.value.subrase?.Name ?: "Subrace detail")
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
                    RaceInfoCard(
                        label = "Rasa: ",
                        value = viewState.value.subrase?.Name ?: "Nezadáno")
                }
                item {
//                    RacesCard(
//                        name = viewState.value.subrase?.Mana?.MaxMana ?: 0,
//                        popis = viewState.value.subrase?.Mana?.MaxMana ?: 0
//                    )
                }
            }
        }
    }
}

@Composable
fun RacesCard(name: String,  popis: String) {
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
            Text(text = "Název: $name", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Popis: $popis", style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Composable
fun RaceInfoCard(label: String, value: String) {

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
fun RaceDetailScreenPreview(){
    RaceDetailScreen(RasesDetailViewModel(),null,"1")
}