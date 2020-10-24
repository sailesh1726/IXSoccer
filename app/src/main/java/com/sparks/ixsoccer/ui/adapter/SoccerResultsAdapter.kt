package com.sparks.ixsoccer.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sparks.ixsoccer.R
import com.sparks.ixsoccer.data.datamodel.HeaderItem
import com.sparks.ixsoccer.data.datamodel.ListItem
import com.sparks.ixsoccer.data.datamodel.SoccerItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SoccerResultsAdapter : SoccerBaseAdapter() {


    class SoccerViewHolder(v: View) : SoccerBaseViewHolder(v) {
        val homeTeamScore: TextView = v.findViewById(R.id.homeTeamScore)
        val awayTeamScore: TextView = v.findViewById(R.id.awayTeamScore)
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
        val type = getItemViewType(position)
        if (type == ListItem.TYPE_SOCCER) {
            val soccerItem: SoccerItem = listItem[position] as SoccerItem
            val holder: SoccerViewHolder = viewHolder as SoccerViewHolder
            holder.homeTeamScore.visibility = View.VISIBLE
            holder.awayTeamScore.visibility = View.VISIBLE

            holder.homeTeamScore.text = soccerItem.soccerEvent.score.home.toString()
            holder.awayTeamScore.text = soccerItem.soccerEvent.score.away.toString()

            if ("home" == soccerItem.soccerEvent.score.winner) {
                holder.homeTeamScore.setTextColor(Color.BLUE)
                holder.awayTeamScore.setTextColor(Color.GRAY)
            } else if ("away" == soccerItem.soccerEvent.score.winner) {
                holder.awayTeamScore.setTextColor(Color.BLUE)
                holder.homeTeamScore.setTextColor(Color.GRAY)
            } else {
                holder.awayTeamScore.setTextColor(Color.GRAY)
                holder.homeTeamScore.setTextColor(Color.GRAY)
            }
        }
    }
}
