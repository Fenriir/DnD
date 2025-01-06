package com.example.dnd_app.viewstates

import com.example.dnd_app.models.Characters
import com.example.dnd_app.models.Feats

data class FeatyViewState(
    val isLoading: Boolean = false,
    val search: String = "",
    val featyList: List<Feats> = emptyList()
)
