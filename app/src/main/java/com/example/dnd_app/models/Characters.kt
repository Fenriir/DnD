package com.example.dnd_app.models

import java.util.Dictionary


data class Characters(
    val Id: String,
    val CharId: String, // Character ID
    val Name: String, // Character Name
    val Lvl: Int, // Level
    val HP: HP, // Health Points
    val DC: Int, // Difficulty Class
    val Rasa: String, // Race
    val Mana: Mana // Mana points
)

data class HP(
    val AktHp: Int, // Current HP
    val MaxHp: Int // Maximum HP
)

data class Mana(
    val AktMana: Int, // Current Mana
    val MaxMana: Int // Maximum Mana
)

