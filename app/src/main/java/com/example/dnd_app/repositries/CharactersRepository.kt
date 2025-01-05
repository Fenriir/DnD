package com.example.dnd_app.repositries

import android.util.Log
import com.example.dnd_app.api.CharactersApi
import com.example.dnd_app.models.Characters

class CharactersRepository(private val charactersApi: CharactersApi) {

    suspend fun getCharacters(): List<Characters> {
        val characters = charactersApi.getCharacters()
        Log.i("CharactersRepository", "getCharacters: $characters") // Logování výsledku dat z API
        return characters
    }

    suspend fun getCharacter(charId: String): Characters {
        val character = charactersApi.getCharacter(charId)
        Log.i("CharactersRepository", "getCharacter: $character")
        return character
    }

    suspend fun getByName(name: String): List<Characters> {
        val characters = charactersApi.getByName(name)
        Log.i("CharactersRepository", "getByName: $characters")
        return characters
    }

    suspend fun getByRace(race: String): List<Characters> {
        val characters = charactersApi.getByRace(race)
        Log.i("CharactersRepository", "getByRace: $characters")
        return characters
    }

    suspend fun getLvlBiggerThan(lvl: Int): List<Characters> {
        val characters = charactersApi.getLvlBiggerThan(lvl)
        Log.i("CharactersRepository", "getLvlBiggerThan: $characters")
        return characters
    }

    suspend fun postCharacter(newCharacter: Characters): Characters {
        val character = charactersApi.postCharacter(newCharacter)
        Log.i("CharactersRepository", "postCharacter: $character")
        return character
    }

    suspend fun postManyCharacters(newCharacters: List<Characters>): List<Characters> {
        val characters = charactersApi.postManyCharacters(newCharacters)
        Log.i("CharactersRepository", "postManyCharacters: $characters")
        return characters
    }

    suspend fun updateCharacter(charId: String, updatedCharacter: Characters): Void {
        val result = charactersApi.updateCharacter(charId, updatedCharacter)
        Log.i("CharactersRepository", "updateCharacter for id $charId")
        return result
    }

    suspend fun deleteCharacter(charId: String): Void {
        val result = charactersApi.deleteCharacter(charId)
        Log.i("CharactersRepository", "deleteCharacter for id $charId")
        return result
    }
}