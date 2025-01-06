package com.example.dnd_app.viewstates

import com.example.dnd_app.models.Feats

data class FeatyDetailViewState(
    val isLoading: Boolean = false,
    val search: String = "",
    val featsId: String="",
    val featy: Feats? = null
)
