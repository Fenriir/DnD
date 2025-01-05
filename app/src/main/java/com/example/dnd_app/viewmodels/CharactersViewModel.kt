package com.example.dnd_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.models.Characters
import com.example.dnd_app.repositries.CharactersRepository
import com.example.dnd_app.viewstates.CharactersViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel(){
    private val charactersRepository = CharactersRepository(RetrofitInstance.charactersApi)
 //    private val _snackbarMessage = Channel<String>()

    private val _viewState = MutableStateFlow(CharactersViewState())
    val viewState: StateFlow<CharactersViewState> = _viewState.asStateFlow()

    init{
        fetchCharacters()
    }

    private fun fetchCharacters(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val charactersList = charactersRepository.getCharacters()
                _viewState.update { it.copy(charactersList = charactersList) }
            } catch (e: Exception) {
                Log.e("CharactersViewModel", "fetchCharacters: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }

    fun onSearchChange(query:String){
        _viewState.update { it.copy(search = query) }
    }

    fun searchCharacters(){

    }
}