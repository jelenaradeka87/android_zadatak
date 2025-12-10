package com.androidzadatak.model

import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    val id: Int,
    val sportId: Int,
    val name: String,
    val competitionIconUrl: String,
    val competitionIconLocalPath: String? = null,
)