package com.androidzadatak.repository.remote

import com.androidzadatak.model.Competition
import com.androidzadatak.model.Match
import com.androidzadatak.model.Sport
import retrofit2.http.GET

interface ApiService {

    @GET("sports")
    suspend fun getAllSports(): List<Sport>

    @GET("matches")
    suspend fun getMatches(): List<Match>

    @GET("competitions")
    suspend fun getCompetitions(): List<Competition>

}

