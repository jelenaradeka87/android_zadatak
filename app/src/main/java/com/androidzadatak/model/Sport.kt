package com.androidzadatak.model

import kotlinx.serialization.Serializable

@Serializable
data class Sport(
    val id: Int,
    val name: String,
    val sportIconUrl: String,
    val localIconPath: String? = null
)