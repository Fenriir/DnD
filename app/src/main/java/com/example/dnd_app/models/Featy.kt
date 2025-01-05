package com.example.dnd_app.models

data class Feats(
    val Id: String,
    val FeatsId: String,
    val Name: String,
    val Featy: Featy
)

data class Featy(
    val Zaklad: Map<String, String>
)