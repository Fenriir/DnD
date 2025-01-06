package com.example.dnd_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.repositries.CharactersRepository
import com.example.dnd_app.repositries.FeatyRepository
import com.example.dnd_app.viewstates.CharactersViewState
import com.example.dnd_app.viewstates.FeatyViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeatyViewModel: ViewModel(){
    private val featyRepository = FeatyRepository(RetrofitInstance.featyApi)

    private val _viewState = MutableStateFlow(FeatyViewState())
    val viewState: StateFlow<FeatyViewState> = _viewState.asStateFlow()

    init{
        fetchFeaty()
    }

    private fun fetchFeaty(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val featyList = featyRepository.getFeats()
                _viewState.update { it.copy(featyList = featyList) }
            } catch (e: Exception) {
                Log.e("FeatyViewModel", "fetchFeaty: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }

    fun onSearchChange(query:String){
        _viewState.update { it.copy(search = query) }
    }

    fun searchFeaty(){

    }
}
