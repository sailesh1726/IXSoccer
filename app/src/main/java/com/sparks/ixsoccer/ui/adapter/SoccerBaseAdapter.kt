package com.sparks.ixsoccer.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sparks.ixsoccer.R
import com.sparks.ixsoccer.data.datamodel.HeaderItem
import com.sparks.ixsoccer.data.datamodel.ListItem
import com.sparks.ixsoccer.data.datamodel.SoccerItem
import com.sparks.ixsoccer.util.XISoccerUtils
import kotlin.collections.ArrayList


abstract class SoccerBaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var listItem: List<ListItem> = ArrayList()

    open class SoccerBaseViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val leagueName: TextView = v.findViewById(R.id.leagueName)
        val stadiumName: TextView = v.findViewById(R.id.stadiumName)
        val dateTextView: TextView = v.findViewById(R.id.dateTextView)
        val homeTeamName: TextView = v.findViewById(R.id.homeTeamName)
        val awayTeamName: TextView = v.findViewById(R.id.awayTeamName)
    }

    class HeaderViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.monthYear)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val type = getItemViewType(position)
        if (type == ListItem.TYPE_HEADER) {
            val headerItem = listItem[position] as HeaderItem
            val holder = viewHolder as HeaderViewHolder
            holder.name.text = headerItem.monthYear
        } else {
            val soccerItem: SoccerItem = listItem[position] as SoccerItem
            val holder: SoccerBaseViewHolder = viewHolder as SoccerBaseViewHolder
            holder.leagueName.text =
                soccerItem.soccerEvent.competitionStage.competition.name.toUpperCase()
            holder.stadiumName.text = soccerItem.soccerEvent.venue.name + " | "
            holder.dateTextView.text = XISoccerUtils.getDateFormatted(soccerItem.soccerEvent.date)
            holder.homeTeamName.text = soccerItem.soccerEvent.homeTeam.name
            holder.awayTeamName.text = soccerItem.soccerEvent.awayTeam.name
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listItem[position].getType()
    }

    fun updateList(list: List<ListItem>) {
        listItem = list
        notifyDataSetChanged()
    }
}
