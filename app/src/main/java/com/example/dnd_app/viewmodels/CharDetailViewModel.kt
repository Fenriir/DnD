package com.example.dnd_app.viewmodels

import androidx.lifecycle.ViewModel
import com.example.dnd_app.viewstates.CharDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CharDetailViewModel : ViewModel(){
    private val _viewState = MutableStateFlow(CharDetailViewState(""))
    val viewState = _viewState.asStateFlow()

    fun setCharId(charId:String){
        _viewState.update { it.copy(charId = charId) }
    }

    fun initLoad(charId: String){
        setCharId(charId)
        //load from api
    }

}