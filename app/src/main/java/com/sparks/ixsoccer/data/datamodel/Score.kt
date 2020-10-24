package com.sparks.ixsoccer.data.datamodel

import androidx.annotation.Keep

@Keep
data class Score(
    val home: Int,
    val away: Int,
    val winner: String
)
