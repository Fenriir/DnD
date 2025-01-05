package com.example.dnd_app.viewstates

import com.example.dnd_app.models.Characters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersViewState (
    val isLoading: Boolean = false,
    val charactersList: List<Characters> = emptyList(),

 //   val snackbarMessage: Flow<String> = emptyFlow(),
    val search: String = ""
)