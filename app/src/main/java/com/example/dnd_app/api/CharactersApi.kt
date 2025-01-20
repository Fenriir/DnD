package com.example.dnd_app.api

import com.example.dnd_app.models.Characters
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CharactersApi {
    @GET("Character")
    suspend fun getCharacters(): List<Characters>

    @GET("Character/{charId}")
    suspend fun getCharacter(@Path("charId") charId: String): Characters

    @GET("Character/{name}")
    suspend fun getByName(@Path("name") name: String): List<Characters>

    @GET("Character/{race}")
    suspend fun getByRace(@Path("race") race: String):List<Characters>

    @GET("Character/{lvl}")
    suspend fun getLvlBiggerThan(@Path("lvl") lvl: Int): List<Characters>

    @POST("Character")
    suspend fun postCharacter(@Body newCharacter: Characters):Characters

    @POST("createMany")
    suspend fun postManyCharacters(@Body newCharacters: List<Characters>): List<Characters>

    @PUT("Character/{charId}")
    suspend fun updateCharacter(@Path("charId") charId: String, @Body updatedCharacter: Characters): Unit

    @DELETE("Character/{charId}")
    suspend fun deleteCharacter(@Path("charId") charId: String): Unit

    @GET("Character/search/{name}")
    suspend fun searchCharacter(@Path("name") name: String): List<Characters>

}