package com.sparks.ixsoccer.data.network

import com.sparks.ixsoccer.data.datamodel.SoccerData
import retrofit2.http.GET

interface SoccerApi {
    @GET("/cdn-og-test-api/test-task/fixtures.json")
    suspend fun getFixtures(): List<SoccerData>

    @GET("/cdn-og-test-api/test-task/results.json")
    suspend fun getResults(): List<SoccerData>
}
