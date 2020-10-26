package com.sparks.ixsoccer.util

import com.sparks.ixsoccer.data.datamodel.HeaderItem
import com.sparks.ixsoccer.data.datamodel.ListItem
import com.sparks.ixsoccer.data.datamodel.SoccerData
import com.sparks.ixsoccer.data.datamodel.SoccerItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Collections.sort

object XISoccerUtils {
    const val FIXTURES: String = "FIXTURES"
    const val RESULTS: String = "RESULTS"
    internal fun getDay(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("EEE", Locale.US)
        return fmt.format(date)
    }

    internal fun getDate(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("dd", Locale.US)
        return fmt.format(date)
    }

    internal fun getDateFormatted(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.US)
        return fmt.format(date)
    }

    internal fun getListItems(list: List<SoccerData>): List<ListItem> {
        sort(list) { t, t2 -> t.date.compareTo(t2.date) }
        val finalList: MutableList<ListItem> = ArrayList()
        val map: TreeMap<String, MutableList<SoccerData>> =
                TreeMap(SortByMonth())
        for (soccer in list) {
            val fmt: DateFormat = SimpleDateFormat("MMMM yyyy", Locale.US)
            val month: String = fmt.format(soccer.date)
            if (!map.containsKey(month)) {
                map[month] = ArrayList()
            }
            map[month]?.add(soccer)
        }

        for (monthYear in map.keys) {
            val header = HeaderItem(monthYear)
            finalList.add(header)
            val iterator = map[monthYear]?.listIterator()
            if (iterator != null) {
                while (iterator.hasNext()) {
                    val item = SoccerItem(iterator.next())
                    finalList.add(item)
                }
            }
        }

        return finalList
    }

    internal fun getListItemsByLeague(list: List<SoccerData>): List<ListItem> {
        sort(list) { t, t2 -> t.date.compareTo(t2.date) }
        val finalList: MutableList<ListItem> = ArrayList()
        val map: TreeMap<String, MutableList<SoccerData>> =
                TreeMap()
        for (soccer in list) {
            val leagueName = soccer.competitionStage.competition.name
            if (!map.containsKey(leagueName)) {
                map[leagueName] = ArrayList()
            }
            map[leagueName]?.add(soccer)
        }

        for (monthYear in map.keys) {
            val header = HeaderItem(monthYear)
            finalList.add(header)
            val iterator = map[monthYear]?.listIterator()
            if (iterator != null) {
                while (iterator.hasNext()) {
                    val item = SoccerItem(iterator.next())
                    finalList.add(item)
                }
            }
        }

        return finalList
    }


    internal class SortByMonth : Comparator<String> {

        override fun compare(a: String, b: String): Int {
            val fmt: DateFormat = SimpleDateFormat("MMMM yyyy", Locale.US)
            val p0: Date = fmt.parse(a)
            val p1: Date = fmt.parse(b)

            return p0.compareTo(p1)
        }
    }
}