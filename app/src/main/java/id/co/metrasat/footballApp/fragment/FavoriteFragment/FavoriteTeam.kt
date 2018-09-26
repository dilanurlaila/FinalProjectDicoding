package id.co.metrasat.footballApp.fragment.FavoriteFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.model.TeamFavorite


class FavoriteTeam : Fragment() {
    private var teamFavorite : MutableList<TeamFavorite> = mutableListOf()
    private lateinit var listTeams : RecyclerView
    private lateinit var pgBar : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FavoriteTeam().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
