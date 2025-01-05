package com.example.dnd_app.repositries

import android.util.Log
import com.example.dnd_app.api.FeatyApi
import com.example.dnd_app.models.Feats

class FeatyRepository(private val featyApi: FeatyApi) {

    suspend fun getFeats(): List<Feats> {
        val feats = featyApi.getFeats()
        Log.i("FeatsRepository", "getFeats: $feats")
        return feats
    }

    suspend fun getFeat(featsId: String): Feats {
        val feat = featyApi.getFeat(featsId)
        Log.i("FeatsRepository", "getFeat: $feat")
        return feat
    }

    suspend fun getByName(name: String): List<Feats> {
        val feats = featyApi.getByName(name)
        Log.i("FeatsRepository", "getByName: $feats")
        return feats
    }

    suspend fun postFeat(newFeat: Feats): Feats {
        val feat = featyApi.postFeat(newFeat)
        Log.i("FeatsRepository", "postFeat: $feat")
        return feat
    }

    suspend fun postManyFeats(newFeats: List<Feats>): List<Feats> {
        val feats = featyApi.postManyFeats(newFeats)
        Log.i("FeatsRepository", "postManyFeats: $feats")
        return feats
    }

    suspend fun updateFeat(featsId: String, updatedFeat: Feats): Void {
        val result = featyApi.updateFeat(featsId, updatedFeat)
        Log.i("FeatsRepository", "updateFeat for id $featsId")
        return result
    }

    suspend fun deleteFeat(featsId: String): Void {
        val result = featyApi.deleteFeat(featsId)
        Log.i("FeatsRepository", "deleteFeat for id $featsId")
        return result
    }
}