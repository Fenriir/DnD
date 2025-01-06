package com.example.dnd_app.viewstates

import com.example.dnd_app.models.Characters
import com.example.dnd_app.models.Race

data class EditCharViewState(
    val isLoading: Boolean = false,
    val search: String = "",
    val charId: String="",
    val raceId: String="",
    val character: Characters? = null,
    val race: Race? = null
)
