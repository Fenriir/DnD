package com.example.dnd_app.viewstates

data class CharactersViewState (
    val isLoading: Boolean = false,
    val charactersNamesLists: List<String> = emptyList(),
)