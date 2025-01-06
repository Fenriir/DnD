package com.example.dnd_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.models.Characters
import com.example.dnd_app.repositries.CharactersRepository
import com.example.dnd_app.repositries.RasesRepository
import com.example.dnd_app.viewstates.NewCharViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewCharViewModel: ViewModel(){

    private val charactersRepository = CharactersRepository(RetrofitInstance.charactersApi)
    private val racesRepository = RasesRepository(RetrofitInstance.rasesApi)

    private val _viewState = MutableStateFlow(NewCharViewState())
    val viewState = _viewState.asStateFlow()


    private var originalCharacterList: List<Characters> = emptyList()

    fun refreshCharList() {
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true) }
            val characters = charactersRepository.getCharacters()
            originalCharacterList = characters // Uložení původního seznamu
            _viewState.update { it.copy(charactersList = characters, isLoading = false) }
        }
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

    fun createCharacter(newCharacter: Characters) {
        viewModelScope.launch {
            try {
                charactersRepository.postCharacter(newCharacter)
                refreshCharList()
            } catch (e: Exception) {
                Log.e("CharactersViewModel", "Error creating character: ${e.message}")
            }
        }
    }


}
