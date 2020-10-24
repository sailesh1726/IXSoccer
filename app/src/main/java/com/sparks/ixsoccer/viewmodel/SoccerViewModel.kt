package com.sparks.ixsoccer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sparks.ixsoccer.data.datamodel.SoccerData
import com.sparks.ixsoccer.data.repository.SoccerRepository
import com.sparks.ixsoccer.util.XISoccerUtils
import kotlinx.coroutines.Dispatchers


class SoccerViewModel(soccerRepository: SoccerRepository) : ViewModel() {
    val fixtures = liveData(Dispatchers.IO) {
        var fixtureList: List<SoccerData> = emptyList()
        try {
            fixtureList = soccerRepository.loadSoccerFixtures()
        } catch (e: Exception) {
            e.stackTrace
        }
        val finalList = XISoccerUtils.getListItems(fixtureList)
        emit(finalList)
    }

    val results = liveData(Dispatchers.IO) {
        var resultList: List<SoccerData> = emptyList()
        try {
            resultList = soccerRepository.loadSoccerResults()
        } catch (e: Exception) {

        }

        val finalList = XISoccerUtils.getListItems(resultList)
        emit(finalList)
    }
}
