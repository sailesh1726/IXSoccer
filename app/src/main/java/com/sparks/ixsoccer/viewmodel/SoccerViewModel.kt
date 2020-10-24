package com.sparks.ixsoccer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sparks.ixsoccer.data.datamodel.HeaderItem
import com.sparks.ixsoccer.data.datamodel.ListItem
import com.sparks.ixsoccer.data.datamodel.SoccerData
import com.sparks.ixsoccer.data.datamodel.SoccerItem
import com.sparks.ixsoccer.data.repository.SoccerRepository
import kotlinx.coroutines.Dispatchers
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class SoccerViewModel(soccerRepository: SoccerRepository) : ViewModel() {
    val fixtures = liveData(Dispatchers.IO) {
        var fixtureList: List<SoccerData> = emptyList()
        try {
            fixtureList = soccerRepository.loadSoccerFixtures()
        } catch (e: Exception) {
            e.stackTrace
        }
        val finalList=getItems(fixtureList)
        emit(finalList)
    }

    private fun getItems(list: List<SoccerData>): List<ListItem> {
        val finalList:MutableList<ListItem> = ArrayList<ListItem>()
        val map: MutableMap<String,MutableList<SoccerData>> = HashMap<String,MutableList<SoccerData>>()
        for(soccer in list){
            val fmt: DateFormat = SimpleDateFormat("MMMM yyyy", Locale.US)
            val month: String= fmt.format(soccer.date)
            if(!map.containsKey(month)){
                map[month] = ArrayList<SoccerData>()
            }
            map[month]?.add(soccer)
        }

        for (monthYear in map.keys) {
            val header = HeaderItem(monthYear)
            finalList.add(header)
            val iterator= map[monthYear]?.listIterator()
            if (iterator != null) {
                while(iterator.hasNext()){
                    val item = SoccerItem(iterator.next())
                    finalList.add(item)
                }
            }
        }

        return finalList;
    }

    val results = liveData(Dispatchers.IO) {
        var resultList: List<SoccerData> = emptyList()
        try {
            resultList = soccerRepository.loadSoccerResults()
        } catch (e: Exception) {

        }

        val finalList=getItems(resultList)
        emit(finalList)
    }
}
