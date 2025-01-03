package com.example.dnd_app.viewmodels

import androidx.lifecycle.ViewModel
import com.example.dnd_app.viewstates.CharactersViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharactersViewModel : ViewModel(){
    private val _viewState = MutableStateFlow(CharactersViewState())
    val viewState: StateFlow<CharactersViewState> = _viewState.asStateFlow()

}