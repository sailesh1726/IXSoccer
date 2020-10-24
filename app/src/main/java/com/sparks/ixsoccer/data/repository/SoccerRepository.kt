package com.sparks.ixsoccer.data.repository

import com.sparks.ixsoccer.data.datamodel.SoccerData
import com.sparks.ixsoccer.data.network.SoccerApi

interface SoccerRepository {
    suspend fun loadSoccerFixtures(): List<SoccerData>

    suspend fun loadSoccerResults(): List<SoccerData>

    companion object {
        @Volatile
        private var instance: SoccerRepository? = null
        fun getInstance(soccerApi: SoccerApi) =
            instance ?: synchronized(this) {
                instance ?: SoccerRepositoryImpl(soccerApi).also { instance = it }
            }
    }
}