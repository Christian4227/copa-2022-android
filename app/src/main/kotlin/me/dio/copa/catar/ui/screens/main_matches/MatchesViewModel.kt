package me.dio.copa.catar.ui.screens.main_matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.dio.copa.catar.domain.model.Match
import me.dio.copa.catar.domain.repositories.MatchesRepository
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val repository: MatchesRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MatchesUiState>(MatchesUiState.Loading)
    val state: StateFlow<MatchesUiState> = _state.asStateFlow()

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            repository.getMatches()
                .catch { _state.value = MatchesUiState.Error }
                .collect { matches ->
                    _state.value = MatchesUiState.Success(matches)
                }
        }
    }

    fun toggleNotification(match: Match) {
        viewModelScope.launch {
            if (match.notificationEnabled) {
                repository.disableNotificationFor(match.id)
            } else {
                repository.enableNotificationFor(match.id)
            }
        }
    }
}

sealed interface MatchesUiState {
    object Loading : MatchesUiState
    object Error : MatchesUiState
    data class Success(val matches: List<Match>) : MatchesUiState
}
