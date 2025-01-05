package com.example.dnd_app.repositries

import android.util.Log
import com.example.dnd_app.api.RasesApi
import com.example.dnd_app.models.Race

class RasesRepository(private val racesApi: RasesApi) {

    suspend fun getRaces(): List<Race> {
        val races = racesApi.getRaces()
        Log.i("RacesRepository", "getRaces: $races")
        return races
    }

    suspend fun getRace(raceId: String): Race {
        val race = racesApi.getRace(raceId)
        Log.i("RacesRepository", "getRace: $race")
        return race
    }

    suspend fun getByName(name: String): List<Race> {
        val races = racesApi.getByName(name)
        Log.i("RacesRepository", "getByName: $races")
        return races
    }

    suspend fun postRace(newRace: Race): Race {
        val race = racesApi.postRace(newRace)
        Log.i("RacesRepository", "postRace: $race")
        return race
    }

    suspend fun postManyRaces(newRaces: List<Race>): List<Race> {
        val races = racesApi.postManyRaces(newRaces)
        Log.i("RacesRepository", "postManyRaces: $races")
        return races
    }

    suspend fun updateRace(raceId: String, updatedRace: Race): Void {
        val result = racesApi.updateRace(raceId, updatedRace)
        Log.i("RacesRepository", "updateRace for id $raceId")
        return result
    }

    suspend fun deleteRace(raceId: String): Void {
        val result = racesApi.deleteRace(raceId)
        Log.i("RacesRepository", "deleteRace for id $raceId")
        return result
    }
}