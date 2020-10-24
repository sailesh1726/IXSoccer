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


class SoccerFixturesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listItem: List<ListItem> = ArrayList()

    class SoccerViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val leagueName: TextView = v.findViewById(R.id.leagueName)
        val stadiumName: TextView = v.findViewById(R.id.stadiumName)
        val dateTextView: TextView = v.findViewById(R.id.dateTextView)
        val homeTeamName: TextView = v.findViewById(R.id.homeTeamName)
        val awayTeamName: TextView = v.findViewById(R.id.awayTeamName)
//        val homeTeamScore: TextView = v.findViewById(R.id.homeTeamScore)
//        val awayTeamScore: TextView = v.findViewById(R.id.awayTeamScore)
        val dateLayout: View = v.findViewById(R.id.dateLayout)
        val dateFixtures: TextView = v.findViewById(R.id.dateFixtures)
        val dayFixtures: TextView = v.findViewById(R.id.dayFixtures)
        val postponed: TextView = v.findViewById(R.id.postponed)
    }

    class HeaderViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.monthYear)
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

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val type = getItemViewType(position)
        if (type == ListItem.TYPE_HEADER) {
            val headerItem = listItem[position] as HeaderItem
            val holder = viewHolder as HeaderViewHolder
            holder.name.text=headerItem.monthYear
        } else {
            val soccerItem: SoccerItem = listItem[position] as SoccerItem
            val holder: SoccerViewHolder = viewHolder as SoccerViewHolder
            holder.leagueName.text=soccerItem.soccerEvent.competitionStage.competition.name.toUpperCase()
            holder.stadiumName.text= soccerItem.soccerEvent.venue.name+" | "
            holder.dateTextView.text=getDateFormatted(soccerItem.soccerEvent.date)
            if("postponed"==soccerItem.soccerEvent.state){
                holder.dateTextView.setTextColor(Color.RED)
                holder.postponed.visibility = View.VISIBLE
            }else{
                holder.dateTextView.setTextColor(Color.GRAY)
                holder.postponed.visibility = View.GONE
            }
            holder.homeTeamName.text=soccerItem.soccerEvent.homeTeam.name
            holder.awayTeamName.text=soccerItem.soccerEvent.awayTeam.name
            holder.dateLayout.visibility = View.VISIBLE
            holder.dateFixtures.text=getDate(soccerItem.soccerEvent.date)
            holder.dayFixtures.text=getDay(soccerItem.soccerEvent.date).toUpperCase()

//            if("home" == soccerItem.soccerEvent.score.winner){
//                holder.homeTeamScore.setTextColor(Color.BLUE)
//                holder.awayTeamScore.setTextColor(Color.GRAY)
//            }else if("away" == soccerItem.soccerEvent.score.winner){
//                holder.awayTeamScore.setTextColor(Color.BLUE)
//                holder.homeTeamScore.setTextColor(Color.GRAY)
//            }else{
//                holder.awayTeamScore.setTextColor(Color.GRAY)
//                holder.homeTeamScore.setTextColor(Color.GRAY)
//            }
        }
    }

    private fun getDay(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("EEE", Locale.US)
        return fmt.format(date)
    }

    private fun getDate(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("dd", Locale.US)
        return fmt.format(date)
    }

    private fun getDateFormatted(date: Date): String {
        val fmt: DateFormat = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.US)
        return fmt.format(date)
    }

    override fun getItemViewType(position: Int): Int {
        return listItem[position].getType()
    }
    fun updateFixtures(list: List<ListItem>) {
        listItem = list
        notifyDataSetChanged()
    }
}
