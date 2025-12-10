package com.androidzadatak.repository

import android.util.Log
import com.androidzadatak.model.Competition
import com.androidzadatak.model.Match
import com.androidzadatak.model.Resource
import com.androidzadatak.model.Sport
import com.androidzadatak.repository.remote.ApiService
import com.androidzadatak.util.Constants.MAIN_REPO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository(
    private val api: ApiService,
    private val fileRepo: FileRepository
) {


    fun getSports(): Flow<Resource<List<Sport>>> = flow {
        Log.d(MAIN_REPO, "Reading cached sports from local storage")
        val cached = fileRepo.readSports()
        if (cached.isEmpty()) {
            Log.d(MAIN_REPO, "No cached sports found, emitting Loading state")
            emit(Resource.Loading(cached))
        } else {
            Log.d(MAIN_REPO, "Cached sports found: ${cached.size}, emitting Success state")
            emit(Resource.Success(cached))
        }

        try {
            Log.d(MAIN_REPO, "Fetching sports from remote API")
            val remote = api.getAllSports()
            Log.d(MAIN_REPO, "Fetched ${remote.size} sports from API, saving to local storage")
            fileRepo.saveSports(remote)
            emit(Resource.Success(remote))
        } catch (e: Exception) {
            Log.e(MAIN_REPO, "Failed to fetch sports from API: ${e.message}", e)
            emit(Resource.Error(e.message ?: "Error fetching sports", cached))
        }
    }

    fun getMatches(): Flow<Resource<List<Match>>> = flow {
        Log.d(MAIN_REPO, "Reading cached matches from local storage")
        val cached = fileRepo.readMatches()
        if (cached.isEmpty()) {
            Log.d(MAIN_REPO, "No cached matches found, emitting Loading state")
            emit(Resource.Loading(cached))
        } else {
            Log.d(MAIN_REPO, "Cached matches found: ${cached.size}, emitting Success state")
            emit(Resource.Success(cached))
        }

        try {
            Log.d(MAIN_REPO, "Fetching matches from remote API")
            val remote = api.getMatches()
            Log.d(MAIN_REPO, "Fetched ${remote.size} matches from API, saving to local storage")
            fileRepo.saveMatches(remote)
            emit(Resource.Success(remote))
        } catch (e: Exception) {
            Log.e(MAIN_REPO, "Failed to fetch matches from API: ${e.message}", e)
            emit(Resource.Error(e.message ?: "Error fetching matches", cached))
        }
    }

    fun getCompetitions(): Flow<Resource<List<Competition>>> = flow {
        Log.d(MAIN_REPO, "Reading cached competitions from local storage")
        val cached = fileRepo.readCompetitions()
        if (cached.isEmpty()) {
            Log.d(MAIN_REPO, "No cached competitions found, emitting Loading state")
            emit(Resource.Loading(cached))
        } else {
            Log.d(MAIN_REPO, "Cached competitions found: ${cached.size}, emitting Success state")
            emit(Resource.Success(cached))
        }

        try {
            Log.d(MAIN_REPO, "Fetching competitions from remote API")
            val remote = api.getCompetitions()
            Log.d(MAIN_REPO, "Fetched ${remote.size} competitions from API, saving to local storage")
            fileRepo.saveCompetitions(remote)
            emit(Resource.Success(remote))
        } catch (e: Exception) {
            Log.e(MAIN_REPO, "Failed to fetch competitions from API: ${e.message}", e)
            emit(Resource.Error(e.message ?: "Error fetching competitions", cached))
        }
    }
}