package com.androidzadatak.util

import android.content.Context
import com.androidzadatak.R

object TranslationUtil {

    fun translate(context: Context, key: String?): String {
        if (key.isNullOrBlank()) return ""

        return when (key.trim().lowercase()) {
            "football" -> context.getString(R.string.football)
            "basketball" -> context.getString(R.string.basketball)
            "volleyball" -> context.getString(R.string.volleyball)
            "tennis" -> context.getString(R.string.tennis)
            "handball" -> context.getString(R.string.handball)
            else -> key
        }
    }
}