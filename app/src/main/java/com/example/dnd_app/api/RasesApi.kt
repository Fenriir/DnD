package com.example.dnd_app.api

import com.example.dnd_app.models.Race
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RasesApi {

    @GET
    suspend fun getRaces(): List<Race>

    @GET("{raceId}")
    suspend fun getRace(@Path("raceId") raceId: String): Race

    @GET("Name/{name}")
    suspend fun getByName(@Path("name") name: String):List<Race>

    @POST
    suspend fun postRace(@Body newRace: Race): Race

    @POST("createMany")
    suspend fun postManyRaces(@Body newRaces: List<Race>): List<Race>

    @PUT("{raceId}")
    suspend fun updateRace(@Path("raceId") raceId: String, @Body updatedRace: Race): Void

    @DELETE("{raceId}")
    suspend fun deleteRace(@Path("raceId") raceId: String): Void

}