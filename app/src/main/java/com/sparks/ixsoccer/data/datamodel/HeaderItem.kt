package com.sparks.ixsoccer.data.datamodel

import com.sparks.ixsoccer.data.datamodel.ListItem.Companion.TYPE_HEADER

data class HeaderItem(var monthYear: String) : ListItem{
    override fun getType(): Int {
        return TYPE_HEADER
    }
}