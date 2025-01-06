package com.example.dnd_app.viewstates

import com.example.dnd_app.models.Characters

data class CharactersViewState (
    val isLoading: Boolean = false,
    val charactersList: List<Characters> = emptyList(),
    val search: String = ""
)