package com.example.dnd_app.viewmodels

import android.app.Application
import android.content.SharedPreferences
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.controllers.MemoryManager
import com.example.dnd_app.models.Characters
import com.example.dnd_app.repositries.CharactersRepository
import com.example.dnd_app.viewstates.CharactersViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {
    private val charactersRepository = CharactersRepository(RetrofitInstance.charactersApi)
    private val memoryManager = MemoryManager()

    private val _viewState = MutableStateFlow(CharactersViewState())
    val viewState: StateFlow<CharactersViewState> = _viewState.asStateFlow()

    private var originalCharacterList: List<Characters> = emptyList()

    fun refreshCharList() {
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true) }
            val characters = charactersRepository.getCharacters()
            originalCharacterList = characters // Uložení původního seznamu
            _viewState.update { it.copy(charactersList = characters, isLoading = false) }
        }
    }

    init {
        fetchCharacters()
        refreshCharList()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val charactersList = charactersRepository.getCharacters()
                _viewState.update { it.copy(charactersList = charactersList) }
                memoryManager.saveCharacterList("characters", charactersList)
            } catch (e: Exception) {
                Log.e("CharactersViewModel", "fetchCharacters: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }

    fun setMemoryContext(context: Context) {
        memoryManager.setContext(context)
    }

    fun refreshCharacters() {
        memoryManager.loadCharacterList("characters")?.let { characters ->
            _viewState.update { it.copy(charactersList = characters) }
        }
        fetchCharacters()
    }

    fun onSearchChange(query: String) {
        _viewState.update { it.copy(search = query) }
    }

    fun searchCharacters() {
        val currentSearch = _viewState.value.search.lowercase()
        if (currentSearch.isNotBlank()) {
            // Hledání postavy podle jména
            val filteredCharacters = originalCharacterList.filter { character ->
                character.Name.lowercase().contains(currentSearch)
            }
            // Pokud jsou nalezeny nějaké postavy, aktualizuj seznam
            if (filteredCharacters.isNotEmpty()) {
                _viewState.update { it.copy(charactersList = filteredCharacters) }
            } else {
                // _viewState.update { it.copy(charactersList = originalCharacterList) }
            }
        } else {
            // _viewState.update { it.copy(charactersList = originalCharacterList) }
        }
    }

}
