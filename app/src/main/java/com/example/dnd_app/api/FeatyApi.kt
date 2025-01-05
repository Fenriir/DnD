package com.example.dnd_app.api

import com.example.dnd_app.models.Feats
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FeatyApi {

    @GET
    suspend fun getFeats(): List<Feats>

    @GET("{featsId}")
    suspend fun getFeat(@Path("featsId") featsId: String): Feats

    @GET("Name/{name}")
    suspend fun getByName(@Path("name") name: String):List<Feats>

    @POST
    suspend fun postFeat(@Body newFeat: Feats): Feats

    @POST("createMany")
    suspend fun postManyFeats(@Body newFeats: List<Feats>): List<Feats>

    @PUT("{featsId}")
    suspend fun updateFeat(@Path("featsId") featsId: String, @Body updatedFeat: Feats): Void

    @DELETE("{featsId}")
    suspend fun deleteFeat(@Path("featsId") featsId: String): Void

}