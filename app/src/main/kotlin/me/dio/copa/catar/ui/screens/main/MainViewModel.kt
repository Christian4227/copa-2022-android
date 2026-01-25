package me.dio.copa.catar.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent = MutableSharedFlow<MainUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onSettingsClick() {
        viewModelScope.launch {
            _uiEvent.emit(MainUiEvent.NavigateToSettings)
        }
    }

    fun onMatchesClick() {
        viewModelScope.launch {
            _uiEvent.emit(MainUiEvent.NavigateToMatches)
        }
    }

    fun onCountriesClick() {
        viewModelScope.launch {
            _uiEvent.emit(MainUiEvent.NavigateToCountries)
        }
    }
}

sealed interface MainUiEvent {
    object NavigateToSettings : MainUiEvent
    object NavigateToMatches : MainUiEvent
    object NavigateToCountries : MainUiEvent
}
