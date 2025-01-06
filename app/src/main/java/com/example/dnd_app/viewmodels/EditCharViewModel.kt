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
import kotlinx.coroutines.launch

class EditCharViewModel: ViewModel() {
    private val charactersRepository = CharactersRepository(RetrofitInstance.charactersApi)
    private val racesRepository = RasesRepository(RetrofitInstance.rasesApi)

    fun setCharId(charId: String) {
        viewModelScope.launch {
            val character = charactersRepository.getCharacter(charId)
            _viewState.value = _viewState.value.copy(character = character)
        }
    }

    private val _viewState = MutableStateFlow(CharDetailViewState())
    val viewState = _viewState.asStateFlow()

    fun updateCharacterName(newName: String) {
        _viewState.value =
            _viewState.value.copy(character = _viewState.value.character?.copy(Name = newName))
    }

    fun updateCharacterLevel(newLevel: Int) {
        _viewState.value =
            _viewState.value.copy(character = _viewState.value.character?.copy(Lvl = newLevel))
    }

    fun updateCharacterDC(newDC: Int) {
        _viewState.value =
            _viewState.value.copy(character = _viewState.value.character?.copy(DC = newDC))
    }

    fun updateCharacterHp(newAktHp: Int, newMaxHp: Int) {
        val updatedHP = HP(AktHp = newAktHp, MaxHp = newMaxHp)
        _viewState.value =
            _viewState.value.copy(character = _viewState.value.character?.copy(HP = updatedHP))
    }

    fun updateCharacterMana(newAktMana: Int, newMaxMana: Int) {
        val updatedMana = Mana(AktMana = newAktMana, MaxMana = newMaxMana)
        _viewState.value =
            _viewState.value.copy(character = _viewState.value.character?.copy(Mana = updatedMana))
    }

    fun updateCharacterRace(newRaceId: String) {
        _viewState.value =
            _viewState.value.copy(character = _viewState.value.character?.copy(RaceId = newRaceId))
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
}
