package com.sparks.ixsoccer.data.datamodel

import com.sparks.ixsoccer.data.datamodel.ListItem.Companion.TYPE_SOCCER

data class SoccerItem(var soccerEvent: SoccerData) : ListItem {
    override fun getType(): Int {
        return TYPE_SOCCER
    }
}