package com.androidzadatak.repository.remote

import com.androidzadatak.model.Competition
import com.androidzadatak.model.Match
import com.androidzadatak.model.Sport

class ApiImpl(
    private val service: ApiService = ClientProvider.apiService

): ApiService {
    override suspend fun getAllSports(): List<Sport> {
        return service.getAllSports()
    }

    override suspend fun getMatches(): List<Match> {
        return service.getMatches()
    }

    override suspend fun getCompetitions(): List<Competition> {
        return service.getCompetitions()
    }
}