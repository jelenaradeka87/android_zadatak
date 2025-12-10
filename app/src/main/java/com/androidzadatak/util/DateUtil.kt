package com.androidzadatak.util

import android.content.Context
import android.util.Log
import com.androidzadatak.R
import com.androidzadatak.model.Match
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    private val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    private val formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    private val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private fun parseDate(dateString: String): LocalDateTime {
        return try {
            dateString.toLongOrNull()?.let { millis ->
                return LocalDateTime.ofInstant(
                    java.time.Instant.ofEpochMilli(millis),
                    java.time.ZoneId.systemDefault()
                )
            }

            try {
                LocalDateTime.parse(dateString, formatter1)
            } catch (_: Exception) {
                LocalDateTime.parse(dateString, formatter2)
            }

        } catch (e: Exception) {
            Log.e("DateUtil", "Failed to parse date: $dateString. Falling back to now.", e)
            LocalDateTime.now()
        }
    }
    fun formatDateLabel(context: Context, dateString: String): String {
        val matchDateTime = parseDate(dateString)
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
        val today = LocalDate.now()

        return matches
            .map { parseDate(it.date).toLocalDate() }
            .filter { it >= today }
            .distinct()
            .sorted()
            .map { date ->
                val dateString = date.format(outputFormatter)
                dateString to formatDateLabel(context, "$dateString 00:00")
            }
    }

    fun getTimeText(matchDateString: String): String {
        return try {
            val dateTime = parseDate(matchDateString)
            dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
        } catch (e: Exception) {
            Log.e("DateUtil", "Failed to get time from: $matchDateString", e)
            "--:--"
        }
    }
}

