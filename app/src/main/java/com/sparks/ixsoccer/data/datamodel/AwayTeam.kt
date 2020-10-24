package com.sparks.ixsoccer.data.datamodel

import androidx.annotation.Keep

@Keep
data class AwayTeam(
    val id: Int,
    val name: String,
    val shortName: String,
    val abbr: String,
    val alias: String
)
