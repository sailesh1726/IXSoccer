package com.sparks.ixsoccer.data.datamodel

import androidx.annotation.Keep

@Keep
data class HomeTeam(
    val id: Int,
    val name: String,
    val shortName: String,
    val abbr: String,
    val alias: String
)
