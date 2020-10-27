package com.sparks.ixsoccer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sparks.ixsoccer.data.datamodel.ListItem
import com.sparks.ixsoccer.data.datamodel.SoccerData
import com.sparks.ixsoccer.data.repository.SoccerRepository
import com.sparks.ixsoccer.util.XISoccerUtils
import kotlinx.coroutines.Dispatchers


class SoccerViewModel(soccerRepository: SoccerRepository) : ViewModel() {
    var cacheFixtureList: List<SoccerData> = emptyList()
    var cacheResultsList: List<SoccerData> = emptyList()
    val fixtures = liveData(Dispatchers.IO) {
        var finalList: List<ListItem> = emptyList()
        try {
            cacheFixtureList = soccerRepository.loadSoccerFixtures()
            finalList = XISoccerUtils.getListItems(cacheFixtureList)
        } catch (e: Exception) {
            e.stackTrace
        }
        emit(finalList)
    }

    val results = liveData(Dispatchers.IO) {
        var finalList: List<ListItem> = emptyList()
        try {
            cacheResultsList = soccerRepository.loadSoccerResults()
            finalList = XISoccerUtils.getListItems(cacheResultsList)
        } catch (e: Exception) {
            e.stackTrace
        }
        emit(finalList)
    }
}
