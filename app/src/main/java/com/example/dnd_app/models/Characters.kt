package com.example.dnd_app.models


data class Characters(
    val Id: String,
    val CharId: String, // Character ID
    var Name: String, // Character Name
    var Lvl: Int, // Level
    var HP: HP, // Health Points
    var DC: Int, // Difficulty Class
    var RaceId: String, // Race
    var Mana: Mana // Mana points
)

data class HP(
    var AktHp: Int, // Current HP
    var MaxHp: Int // Maximum HP
)

data class Mana(
    var AktMana: Int, // Current Mana
    var MaxMana: Int // Maximum Mana
)

