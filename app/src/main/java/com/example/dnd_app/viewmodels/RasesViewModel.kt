package com.example.dnd_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.repositries.CharactersRepository
import com.example.dnd_app.repositries.RasesRepository
import com.example.dnd_app.viewstates.CharactersViewState
import com.example.dnd_app.viewstates.RasesViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RasesViewModel : ViewModel(){
    private val rasesRepository = RasesRepository(RetrofitInstance.rasesApi)

    private val _viewState = MutableStateFlow(RasesViewState())
    val viewState: StateFlow<RasesViewState> = _viewState.asStateFlow()

    init{
        fetchRaces()
    }

    private fun fetchRaces(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val rasesList = rasesRepository.getRaces()
                _viewState.update { it.copy(racesList = rasesList) }
            } catch (e: Exception) {
                Log.e("RacesViewModel", "fetchRaces: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }

    fun onSearchChange(query:String){
        _viewState.update { it.copy(search = query) }
    }

    fun searchRases(){

    }
}
