package me.dio.copa.catar.data

import me.dio.copa.catar.domain.model.Match
import retrofit2.Call
import retrofit2.http.GET

interface MatchesApi {
    @GET("https://digitalinnovationone.github.io/copa-2022-android/api.json")
    fun getAllMatches(): Call<List<Match>>
}