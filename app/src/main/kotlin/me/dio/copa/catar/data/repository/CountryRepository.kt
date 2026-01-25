package me.dio.copa.catar.data.repository

import me.dio.copa.catar.data.local.Country
import me.dio.copa.catar.data.local.WorldCupData

class CountryRepository {
    fun getParticipants(): List<Country> {
        return WorldCupData.countries
    }
}