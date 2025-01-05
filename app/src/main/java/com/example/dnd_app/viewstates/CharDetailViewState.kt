package com.example.dnd_app.viewstates

import com.example.dnd_app.models.Characters

data class CharDetailViewState (
    val isLoading: Boolean = false,
    val search: String = "",
    val charId: String="",
    val character: Characters? = null
)
