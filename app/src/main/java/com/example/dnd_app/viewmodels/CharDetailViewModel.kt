package com.example.dnd_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.models.Characters
import com.example.dnd_app.repositries.CharactersRepository
import com.example.dnd_app.repositries.RasesRepository
import com.example.dnd_app.ui.screens.CharDetailScreen
import com.example.dnd_app.viewstates.CharDetailViewState
import com.example.dnd_app.viewstates.RasesDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharDetailViewModel : ViewModel(){
    private val charactersRepository = CharactersRepository(RetrofitInstance.charactersApi)
    private val racesRepository = RasesRepository(RetrofitInstance.rasesApi)

    private val _viewState = MutableStateFlow(CharDetailViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewState2 = MutableStateFlow(RasesDetailViewState())
    val viewState2 = _viewState2.asStateFlow()

    private fun initLoad(){
        fetchCharDetail()
    }
    private fun initLoadRace(){
        getRaceDetail()
    }

    private fun fetchCharDetail(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val character = charactersRepository.getCharacter(_viewState.value.charId)
                _viewState.update{it.copy(character = character)}
            } catch (e: Exception) {
                Log.e("CharDetailViewModel", "fetchCharDetail: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }

    private fun getRaceDetail(){
        viewModelScope.launch {
            _viewState2.value = _viewState2.value.copy(isLoading = true)
            try {
                val race = racesRepository.getRace(_viewState2.value.raceId)
                _viewState2.update{it.copy(subrase = race)}
            } catch (e: Exception) {
                Log.e("RaceDetailViewModel", "fetchRaceDetail: ${e.message}")
            }
            _viewState2.value = _viewState2.value.copy(isLoading = false)
        }
    }

    fun setCharId(charId: String) {
        _viewState.update { it.copy(charId = charId) }
        initLoad()
    }

    fun setRaceId(raceId: String) {
        _viewState.update { it.copy(raceId = raceId) }
        initLoadRace()
    }

}