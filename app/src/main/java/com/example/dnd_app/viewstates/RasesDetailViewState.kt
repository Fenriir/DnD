package com.example.dnd_app.viewstates

import com.example.dnd_app.models.Race

data class RasesDetailViewState(
    val isLoading: Boolean = false,
    val search: String = "",
    val raceId: String="",
    val subrase: Race? = null
)
