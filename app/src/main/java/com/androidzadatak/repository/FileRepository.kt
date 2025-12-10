package com.androidzadatak.repository

import com.androidzadatak.model.Competition
import com.androidzadatak.model.Match
import com.androidzadatak.model.Sport

interface FileRepository {
    suspend fun saveSports(sports: List<Sport>)
    suspend fun readSports(): List<Sport>
    suspend fun saveMatches(matches: List<Match>)
    suspend fun readMatches(): List<Match>
    suspend fun saveCompetitions(competitions: List<Competition>)
    suspend fun readCompetitions(): List<Competition>
}