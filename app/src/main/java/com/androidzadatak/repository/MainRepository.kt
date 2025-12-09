package com.androidzadatak.repository

import com.androidzadatak.model.Competition
import com.androidzadatak.model.Match
import com.androidzadatak.model.Sport
import com.androidzadatak.repository.remote.ApiImpl
import com.androidzadatak.repository.remote.ApiService

class MainRepository(
    private val api: ApiService = ApiImpl()
) {

    suspend fun getAllSports(): Result<List<Sport>> {
        return try {
            val sports = api.getAllSports()
            Result.success(sports)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMatches(): Result<List<Match>> {
        return try {
            val matches = api.getMatches()
            Result.success(matches)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCompetitions(): Result<List<Competition>> {
        return try {
            val matches = api.getCompetitions()
            Result.success(matches)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}