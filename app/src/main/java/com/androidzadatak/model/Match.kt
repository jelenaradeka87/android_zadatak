package com.androidzadatak.model

import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamAvatar: String,
    val homeTeamLocalPath: String? = null,
    val awayTeamAvatar: String,
    val awayTeamLocalPath: String? = null,
    val date: String,
    val status: String,
    val currentTime: String? = null,
    val result: MatchResult? = null,
    val sportId: Int,
    val competitionId: Int
)

@Serializable
data class MatchResult(
    val home: Int,
    val away: Int
)