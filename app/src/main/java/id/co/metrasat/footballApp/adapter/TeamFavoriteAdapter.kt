package id.co.metrasat.footballApp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.R.id.team_badge
import id.co.metrasat.footballApp.R.id.team_name
import id.co.metrasat.footballApp.activity.DetailTeam
import id.co.metrasat.footballApp.model.team.TeamFavorite
import org.jetbrains.anko.*

class TeamFavoriteAdapter(private val context: Context?, private val TeamsFavorite :MutableList<TeamFavorite>)
    : RecyclerView.Adapter<TeamFavoriteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamFavoriteHolder {
        return TeamFavoriteHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = TeamsFavorite.size

    override fun onBindViewHolder(holder: TeamFavoriteHolder, position: Int) {
        holder.bindItem(TeamsFavorite[position])
        holder.itemView.setOnClickListener {
            context?.startActivity<DetailTeam>(
                    DetailTeam.ID_TEAM to TeamsFavorite [position].teamIdFavorite,
                    DetailTeam.NAME_TEAM to TeamsFavorite[position].teamNameFavorite,
                    DetailTeam.YEAR_TEAM to TeamsFavorite[position].teamYearFavorite
            )
        }
    }

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = team_badge
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = team_name
                        textSize = 16f
                    }.lparams { margin = dip(16) }
                }
            }
        }
    }
}

class TeamFavoriteHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)
    fun bindItem(teams: TeamFavorite) {
        Picasso.get().load(teams.teamBadgeFavorite).into(teamBadge)
        teamName.text = teams.teamNameFavorite

    }
}
