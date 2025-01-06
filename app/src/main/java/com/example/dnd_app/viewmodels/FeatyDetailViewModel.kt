package com.example.dnd_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd_app.api.RetrofitInstance
import com.example.dnd_app.repositries.CharactersRepository
import com.example.dnd_app.repositries.FeatyRepository
import com.example.dnd_app.viewstates.CharDetailViewState
import com.example.dnd_app.viewstates.FeatyDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeatyDetailViewModel : ViewModel(){
    private val featyRepository = FeatyRepository(RetrofitInstance.featyApi)

    private val _viewState = MutableStateFlow(FeatyDetailViewState())
    val viewState = _viewState.asStateFlow()

    private fun initLoad(){
        fetchFeatDetail()
    }

    private fun fetchFeatDetail(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isLoading = true)
            try {
                val featy = featyRepository.getFeat(_viewState.value.featsId)
                _viewState.update{it.copy(featy = featy)}
            } catch (e: Exception) {
                Log.e("FeatDetailViewModel", "fetchFeatDetail: ${e.message}")
            }
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }

    fun setFeatId(featsId: String) {
        _viewState.update { it.copy(featsId = featsId) }
        initLoad()
    }
}
