package id.co.metrasat.footballApp.adapter

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.co.metrasat.footballApp.activity.DetailMatch
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.model.event.EventFavorite
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class EventFavoriteAdapter (private val context: Context?, private val favorite: MutableList<EventFavorite>):
        RecyclerView.Adapter<EventFavoriteAdapter.FavoriteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
     FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.card_list, parent, false))

    override fun getItemCount(): Int  =favorite.size

    @TargetApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite [position])
        holder.itemView.setOnClickListener {
            context?.startActivity<DetailMatch>(
                    DetailMatch.ID_EVENTS to favorite[position].eventId,
                    DetailMatch.ID_HOME to favorite[position].homeId,
                    DetailMatch.ID_AWAY to favorite[position].awayId,
                    DetailMatch.AWAY_NAME to favorite[position].awayName,
                    DetailMatch.HOME_NAME to favorite[position].homeName)
        }

    }

class FavoriteViewHolder (view: View) :RecyclerView.ViewHolder(view) {
    private val dateEvents = view.findViewById<TextView>(R.id.txt_dateEvent)
    private val strAway = view.findViewById<TextView>(R.id.strAwayTeam)
    private val strHome = view.findViewById<TextView>(R.id.strHomeTeam)
    private val homeScore = view.findViewById<TextView>(R.id.strHomeScore)
    private val awayScore = view.findViewById<TextView>(R.id.strAwayScore)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindItem(favorite: EventFavorite) {
        val formatDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
        val date = formatDate.parse(favorite.eventDate)
        val dateText = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                .format(date).toString()
        dateEvents.text = dateText
        strAway.text = favorite.awayName
        strHome.text = favorite.homeName
        homeScore.text = favorite.homeScore
        awayScore.text = favorite.awayScore

    }

}
}
