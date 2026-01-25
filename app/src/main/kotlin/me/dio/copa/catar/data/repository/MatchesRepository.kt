package me.dio.copa.catar.data.repository

import kotlinx.coroutines.flow.Flow
import me.dio.copa.catar.domain.model.Match
import me.dio.copa.catar.domain.repositories.MatchesRepository
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val repository: MatchesRepository
) {
    suspend fun getMatches(): Flow<List<Match>> = repository.getMatches()
    suspend fun enableNotificationFor(id: String) = repository.enableNotificationFor(id)
    suspend fun disableNotificationFor(id: String) = repository.disableNotificationFor(id)
}
