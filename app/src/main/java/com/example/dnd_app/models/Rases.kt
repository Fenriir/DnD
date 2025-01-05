package com.example.dnd_app.models

data class Race(
    val Id: String,
    val RaceId: String,
    val Name: String,
    val Subrases: List<Subrases> // List of Subraces
)

data class Subrases(
    val name: String,
    val subrace_id: String,
    val popis: String
)
