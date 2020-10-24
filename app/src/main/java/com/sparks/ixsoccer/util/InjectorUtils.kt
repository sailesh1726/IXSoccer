package com.sparks.ixsoccer.util

import com.sparks.ixsoccer.data.network.ApiFactory
import com.sparks.ixsoccer.data.repository.SoccerRepository
import com.sparks.ixsoccer.viewmodel.SoccerViewModelFactory

object InjectorUtils {

    fun provideSoccerViewModelFactory(): SoccerViewModelFactory {
        val soccerRepository = SoccerRepository.getInstance(ApiFactory.soccerApi)
        return SoccerViewModelFactory(soccerRepository)
    }
}