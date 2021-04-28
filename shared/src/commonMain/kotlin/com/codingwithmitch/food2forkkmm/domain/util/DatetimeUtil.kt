package com.codingwithmitch.food2forkkmm.domain.util

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone

class DatetimeUtil
{
    fun now(): LocalDateTime {
        val currentMoment: Instant = Clock.System.now()
        return currentMoment.toLocalDateTime(TimeZone.UTC)
    }

    fun toLocalDate(date: Double): LocalDateTime {
        return Instant.fromEpochMilliseconds(date.toLong()).toLocalDateTime(TimeZone.UTC)
    }

    fun toEpochMilliseconds(date: LocalDateTime): Double {
        return date.toInstant(TimeZone.UTC).toEpochMilliseconds().toDouble()
    }

    fun convertDateToString(date: LocalDateTime?): String? {
        return date?.toString()
    }

}








