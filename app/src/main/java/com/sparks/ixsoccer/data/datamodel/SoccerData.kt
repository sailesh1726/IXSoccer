package com.sparks.ixsoccer.data.datamodel

import androidx.annotation.Keep
import java.util.*
@Keep
data class SoccerData(
    val id: Int,
    val type: String,
    val homeTeam: HomeTeam,
    val awayTeam: AwayTeam,
    val date: Date,
    val competitionStage: CompetitionStage,
    val venue: Venue,
    val state: String,
    val score: Score
)
