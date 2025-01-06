package com.example.dnd_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.models.HP
import com.example.dnd_app.models.Mana
import com.example.dnd_app.repositries.CharactersRepository
import com.example.dnd_app.repositries.RasesRepository
import com.example.dnd_app.viewstates.CharDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharDetailViewModel : ViewModel(){
    private val charactersRepository = CharactersRepository(RetrofitInstance.charactersApi)
    private val racesRepository = RasesRepository(RetrofitInstance.rasesApi)

    private val _viewState = MutableStateFlow(CharDetailViewState())
    val viewState = _viewState.asStateFlow()

    private fun fetchCharDetail(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val character = charactersRepository.getCharacter(_viewState.value.charId)
                _viewState.update{it.copy(character = character)}
                getRaceDetail()
            } catch (e: Exception) {
                Log.e("CharDetailViewModel", "fetchCharDetail: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)

        }
    }

    private fun getRaceDetail(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val race = racesRepository.getRace(_viewState.value.raceId)
                _viewState.update{it.copy(race = race)}
            } catch (e: Exception) {
                Log.e("RaceDetailViewModel", "fetchRaceDetail: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }

//    fun setCharId(charId: String) {
//        _viewState.update { it.copy(charId = charId) }
//        fetchCharDetail()
//    }

    fun setCharId(charId: String) {
        viewModelScope.launch {
            val character = charactersRepository.getCharacter(charId)
            _viewState.value = _viewState.value.copy(character = character)
            fetchCharDetail()
        }
    }

    fun updateCharacterName(newName: String) {
        _viewState.value = _viewState.value.copy(character = _viewState.value.character?.copy(Name = newName))
    }

    fun updateCharacterLevel(newLevel: Int) {
        _viewState.value = _viewState.value.copy(character = _viewState.value.character?.copy(Lvl = newLevel))
    }

    fun updateCharacterDC(newDC: Int) {
        _viewState.value = _viewState.value.copy(character = _viewState.value.character?.copy(DC = newDC))
    }

    fun updateCharacterHp(newAktHp: Int, newMaxHp: Int) {
        val updatedHP = HP(AktHp = newAktHp, MaxHp = newMaxHp)
        _viewState.value = _viewState.value.copy(character = _viewState.value.character?.copy(HP = updatedHP))
    }

    fun updateCharacterMana(newAktMana: Int, newMaxMana: Int) {
        val updatedMana = Mana(AktMana = newAktMana, MaxMana = newMaxMana)
        _viewState.value = _viewState.value.copy(character = _viewState.value.character?.copy(Mana = updatedMana))
    }

    fun updateCharacterRace(newRaceId: String) {
        _viewState.value = _viewState.value.copy(character = _viewState.value.character?.copy(RaceId = newRaceId))
    }

    fun updateCharacter(charId: String) {
        viewModelScope.launch {
            try {
                charactersRepository.updateCharacter(charId, _viewState.value.character!!)
            } catch (e: Exception) {
                Log.e("CharDetailViewModel", "Error updating character: ${e.message}")
            }
        }
    }

    fun deleteCharacter(charId: String) {
        viewModelScope.launch {
            try {
                charactersRepository.deleteCharacter(charId)
                // Můžeš také obnovit seznam postav, pokud je to potřeba
            } catch (e: Exception) {
                Log.e("CharDetailViewModel", "Error deleting character: ${e.message}")
                // Zpracování chyb
            }
        }
    }

}