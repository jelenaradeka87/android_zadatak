package com.androidzadatak.repository

import android.content.Context
import android.util.Log
import com.androidzadatak.model.Competition
import com.androidzadatak.model.Match
import com.androidzadatak.model.Sport
import com.androidzadatak.util.Constants.COMPETITION_FILE
import com.androidzadatak.util.Constants.MATCH_FILE
import com.androidzadatak.util.Constants.SPORT_FILE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.net.URL

class FileRepositoryImpl(private val context: Context) : FileRepository {

    private val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    override suspend fun saveSports(sports: List<Sport>) {
        try {
            val updatedSports = sports.map { sport ->
                val localFile = downloadIcon(sport.sportIconUrl)
                sport.copy(localIconPath = localFile?.absolutePath)
            }

            val file = File(context.filesDir, SPORT_FILE)
            file.writeText(json.encodeToString(updatedSports))
            Log.d("FileRepo", "Saved ${updatedSports.size} sports to $SPORT_FILE")
        } catch (e: Exception) {
            Log.e("FileRepo", "Failed to save sports: ${e.message}", e)
        }
    }

    override suspend fun readSports(): List<Sport> {
        val file = File(context.filesDir, SPORT_FILE)
        if (!file.exists()) return emptyList()
        return json.decodeFromString(file.readText())
    }

    override suspend fun saveMatches(matches: List<Match>) {
        try {
            val updatedMatches = matches.map { match ->
                val localFileHomeTeam = downloadIcon(match.homeTeamAvatar)
                val localFileAwayTeam = downloadIcon(match.awayTeamAvatar)
                match.copy(
                    homeTeamLocalPath = localFileHomeTeam?.absolutePath,
                    awayTeamLocalPath = localFileAwayTeam?.absolutePath
                )
            }

            val file = File(context.filesDir, MATCH_FILE)
            file.writeText(json.encodeToString(updatedMatches))
        } catch (e: Exception) {
            Log.e("FileRepo", "Failed to save matches: ${e.message}", e)
        }
    }

    override suspend fun readMatches(): List<Match> {
        val file = File(context.filesDir, MATCH_FILE)
        if (!file.exists()) return emptyList()
        return json.decodeFromString(file.readText())
    }

    override suspend fun saveCompetitions(competitions: List<Competition>) {
        try {
            val updatedCompetitions = competitions.map { competition ->
                val localFile = downloadIcon(competition.competitionIconUrl)
                competition.copy(competitionIconLocalPath = localFile?.absolutePath)
            }

            val file = File(context.filesDir, COMPETITION_FILE)
            file.writeText(json.encodeToString(updatedCompetitions))
        } catch (e: Exception) {
            Log.e("FileRepo", "Failed to save competitions: ${e.message}", e)
        }
    }

    override suspend fun readCompetitions(): List<Competition> {
        val file = File(context.filesDir, COMPETITION_FILE)
        if (!file.exists()) return emptyList()
        return json.decodeFromString(file.readText())
    }

    private suspend fun downloadIcon(url: String): File? {
        return try {
            val bytes = withContext(Dispatchers.IO) {
                URL(url).openStream().readBytes()
            }

            val fileName = url.substringAfterLast("/")
            val file = File(context.filesDir, fileName)
            file.writeBytes(bytes)
            file
        } catch (e: Exception) {
            null
        }
    }
}
