package com.example.dnd_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.repositries.RasesRepository
import com.example.dnd_app.viewstates.RasesDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RasesDetailViewModel : ViewModel() {
    private val charactersRepository = RasesRepository(RetrofitInstance.rasesApi)

    private val _viewState = MutableStateFlow(RasesDetailViewState())
    val viewState = _viewState.asStateFlow()

    private fun initLoad(){
        fetchRacesDetail()
    }

    private fun fetchRacesDetail(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val race = charactersRepository.getRace(_viewState.value.raceId)
                _viewState.update{it.copy(subrase = race)}
            } catch (e: Exception) {
                Log.e("RaceDetailViewModel", "fetchRaceDetail: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }

    fun setRaceId(raceId: String) {
        _viewState.update { it.copy(raceId = raceId) }
        initLoad()
    }
}