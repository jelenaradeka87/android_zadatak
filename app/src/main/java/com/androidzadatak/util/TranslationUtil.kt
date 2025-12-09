package com.androidzadatak.util

import android.content.Context
import com.androidzadatak.R

object TranslationUtil {

    private val sportMap = mapOf(
        "Football" to R.string.football,
        "Basketball" to R.string.basketball,
        "Volleyball" to R.string.volleyball,
        "Tennis" to R.string.tennis,
        "Handball" to R.string.handball
    )

    fun translate(context: Context, sportName: String): String {
        val resId = sportMap[sportName]
        return if (resId != null) context.getString(resId) else sportName
    }
}