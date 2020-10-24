package com.sparks.ixsoccer.data.repository

import com.sparks.ixsoccer.data.datamodel.SoccerData
import com.sparks.ixsoccer.data.network.SoccerApi

class SoccerRepositoryImpl(private val soccerApi: SoccerApi) : SoccerRepository {
    override suspend fun loadSoccerFixtures(): List<SoccerData> {
        return soccerApi.getFixtures()
    }

    override suspend fun loadSoccerResults(): List<SoccerData> {
        return soccerApi.getResults()
    }
}
