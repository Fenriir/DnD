package com.example.dnd_app.viewstates

import com.example.dnd_app.models.Feats
import com.example.dnd_app.models.Race

data class RasesViewState(
    val isLoading: Boolean = false,
    val search: String = "",
    val racesList: List<Race> = emptyList()
)
