package me.dio.copa.catar.ui.screens.main_matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.dio.copa.catar.domain.model.Match
import me.dio.copa.catar.domain.repositories.MatchesRepository
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val repository: MatchesRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MatchesUiState>(MatchesUiState.Loading)
    val state: StateFlow<MatchesUiState> = _state.asStateFlow()

    private var allMatches: List<Match> = emptyList()

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            repository.getMatches()
                .catch { _state.value = MatchesUiState.Error }
                .collectLatest { matches ->
                    allMatches = matches
                    _state.value = MatchesUiState.Success(matches)
                }
        }
    }

    fun searchMatches(stadiumName: String, startMillis: Long?, endMillis: Long?) {
        val filtered = allMatches.filter { match ->
            val matchesStadium = stadiumName.isEmpty() || match.stadium.name.contains(stadiumName, ignoreCase = true)
            val matchesStart = startMillis?.let {
                val startDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
                match.date.isAfter(startDate) || match.date.isEqual(startDate)
            } ?: true
            val matchesEnd = endMillis?.let {
                val endDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
                match.date.isBefore(endDate) || match.date.isEqual(endDate)
            } ?: true

            matchesStadium && matchesStart && matchesEnd
        }
        _state.value = MatchesUiState.Success(filtered)
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
