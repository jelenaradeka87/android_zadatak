package com.androidzadatak.util

import android.content.Context
import com.androidzadatak.R
import com.androidzadatak.model.Match
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    private val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun formatDateLabel(context: Context, dateString: String): String {
        val matchDateTime = LocalDateTime.parse(dateString, inputFormatter)
        val matchDate = matchDateTime.toLocalDate()
        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)

        return when (matchDate) {
            today -> context.getString(R.string.today)
            tomorrow -> context.getString(R.string.tomorrow)
            else -> matchDate.format(outputFormatter)
        }
    }

    fun getUniqueDates(context: Context, matches: List<Match>): List<Pair<String, String>> {
        return matches
            .map { it.date }
            .map { LocalDateTime.parse(it, inputFormatter).toLocalDate() }
            .distinct()
            .sorted()
            .map { date ->
                val dateString = date.format(outputFormatter)
                dateString to formatDateLabel(context, "$dateString 00:00")
            }
    }

    fun getTimeText(matchDateString: String): String {
        return matchDateString.substring(11, 16)
    }

}

