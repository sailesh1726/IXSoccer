package com.sparks.ixsoccer.data.datamodel

interface ListItem {
    companion object{
        const val TYPE_HEADER = 0
        const val TYPE_SOCCER = 1
    }
    fun getType(): Int
}