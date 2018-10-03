package id.co.metrasat.footballApp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.co.metrasat.footballApp.R.id.*
import id.co.metrasat.footballApp.activity.DetailTeam
import id.co.metrasat.footballApp.model.team.TeamsItem
import org.jetbrains.anko.*

class TeamAdapter(private val context: Context?, private val Teams: MutableList<TeamsItem>)
    : RecyclerView.Adapter<TeamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        return TeamHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.bindItem(Teams[position])
        holder.itemView.setOnClickListener {
            context?.startActivity<DetailTeam>(
                    DetailTeam.ID_TEAM to Teams[position].idTeam,
                    DetailTeam.NAME_TEAM to Teams[position].strTeam,
                    DetailTeam.YEAR_TEAM to Teams[position].intFormedYear
            )
        }
    }

    override fun getItemCount(): Int = Teams.size
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

class TeamHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)
    fun bindItem(teams: TeamsItem) {
        Picasso.get().load(teams.strTeamBadge).into(teamBadge)
        teamName.text = teams.strTeam

    }
}
