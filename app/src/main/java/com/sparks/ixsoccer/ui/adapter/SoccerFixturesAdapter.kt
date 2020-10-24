package com.sparks.ixsoccer.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sparks.ixsoccer.R
import com.sparks.ixsoccer.data.datamodel.ListItem
import com.sparks.ixsoccer.data.datamodel.SoccerItem
import com.sparks.ixsoccer.util.XISoccerUtils


class SoccerFixturesAdapter : SoccerBaseAdapter() {

    class SoccerViewHolder(v: View) : SoccerBaseAdapter.SoccerBaseViewHolder(v) {
        val dateLayout: View = v.findViewById(R.id.dateLayout)
        val dateFixtures: TextView = v.findViewById(R.id.dateFixtures)
        val dayFixtures: TextView = v.findViewById(R.id.dayFixtures)
        val postponed: TextView = v.findViewById(R.id.postponed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mLayoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == ListItem.TYPE_HEADER) {
            val itemView: View =
                mLayoutInflater.inflate(R.layout.list_item_header, parent, false)
            HeaderViewHolder(itemView)
        } else {
            val itemView: View =
                mLayoutInflater.inflate(R.layout.list_item_soccer, parent, false)
            SoccerViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(viewHolder, position)
        val type = getItemViewType(position)
        if (type == ListItem.TYPE_SOCCER) {
            val soccerItem: SoccerItem = listItem[position] as SoccerItem
            val holder: SoccerViewHolder = viewHolder as SoccerViewHolder
            if ("postponed" == soccerItem.soccerEvent.state) {
                holder.dateTextView.setTextColor(Color.RED)
                holder.postponed.visibility = View.VISIBLE
            } else {
                holder.dateTextView.setTextColor(Color.GRAY)
                holder.postponed.visibility = View.GONE
            }
            holder.homeTeamName.text = soccerItem.soccerEvent.homeTeam.name
            holder.awayTeamName.text = soccerItem.soccerEvent.awayTeam.name
            holder.dateLayout.visibility = View.VISIBLE
            holder.dateFixtures.text = XISoccerUtils.getDate(soccerItem.soccerEvent.date)
            holder.dayFixtures.text =
                XISoccerUtils.getDay(soccerItem.soccerEvent.date).toUpperCase()
        }
    }
}
