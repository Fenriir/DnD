package com.example.dnd_app.controllers

import android.content.Context
import android.content.SharedPreferences
import com.example.dnd_app.models.Characters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MemoryManager {
    private lateinit var context: Context
    private val sharedPrefName = "dndPref"

    fun setContext(context: Context) {
        this.context = context
    }

    private fun getPreferences(): SharedPreferences {
        return context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
    }


    fun saveCharacterList(key: String, characters: List<Characters>) {
        val sharedPreferences = getPreferences()
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(characters)
        editor.putString(key, json)
        editor.apply()
    }


    fun loadCharacterList(key: String): List<Characters>? {
        val sharedPreferences = getPreferences()
        val gson = Gson()
        val json = sharedPreferences.getString(key, null)
        return if (json != null) {
            val type = object : TypeToken<List<Characters>>() {}.type
            gson.fromJson(json, type)
        } else {
            null
        }
    }
}