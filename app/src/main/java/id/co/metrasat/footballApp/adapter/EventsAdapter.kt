package id.co.metrasat.footballApp.adapter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.co.metrasat.footballApp.DetailClub
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.model.EventsItem
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class EventsAdapter(private val context: Context?, private val events: List<EventsItem>)
    : RecyclerView.Adapter<EventsAdapter.EventHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventHolder(LayoutInflater.from(context).inflate(R.layout.card_list, parent, false))


    override fun getItemCount(): Int = events.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bindItem(events [position])
        holder.itemView.setOnClickListener {
            context?.startActivity<DetailClub>(
                    DetailClub.ID_EVENTS to events[position].idEvent,
                    DetailClub.ID_HOME to events[position].idHomeTeam,
                    DetailClub.HOME_NAME to events[position].strHomeTeam,
                    DetailClub.ID_AWAY to events[position].idAwayTeam,
                    DetailClub.AWAY_NAME to events[position].strAwayTeam)
        }

    }

    class EventHolder(view:View) :RecyclerView.ViewHolder(view) {
        private val dateEvents = view.findViewById<TextView>(R.id.txt_dateEvent)
        private val strAway = view.findViewById<TextView>(R.id.strAwayTeam)
        private val strHome = view.findViewById<TextView>(R.id.strHomeTeam)
        private val homeScore = view.findViewById<TextView>(R.id.strHomeScore)
        private val awayScore = view.findViewById<TextView>(R.id.strAwayScore)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindItem (events: EventsItem){
            val formatDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
            val date = formatDate.parse(events.dateEvent)
            val dateText = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                    .format(date).toString()
            dateEvents.text = dateText
            strAway.text = events.strAwayTeam
            strHome.text = events.strHomeTeam
            homeScore.text = events.intHomeScore
            awayScore.text = events.intAwayScore

        }
    }
}
