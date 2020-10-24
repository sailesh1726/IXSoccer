package com.sparks.ixsoccer.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object XISoccerUtils {
    internal fun getDay(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("EEE", Locale.US)
        return fmt.format(date)
    }

    internal fun getDate(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("dd", Locale.US)
        return fmt.format(date)
    }

    internal fun getDateFormatted(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.US)
        return fmt.format(date)
    }
}