package id.co.metrasat.footballApp.fragment.fragmentFavorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.TeamFavoriteAdapter
import id.co.metrasat.footballApp.database.database
import id.co.metrasat.footballApp.model.team.TeamFavorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import java.sql.SQLException


class FavoriteTeam : Fragment() {

    private var favorites: MutableList<TeamFavorite> = mutableListOf()
    private lateinit var listTeam: RecyclerView
    private lateinit var mAdapter: TeamFavoriteAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_favorite_team, container, false)
        progressBar = rootView.findViewById(R.id.pgBarTeamFavorite)
        listTeam = rootView.findViewById(R.id.rvTeamFavorite)
        swipeRefresh = rootView.findViewById(R.id.swipeFavoriteTeam)
      
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        listTeam.layoutManager = layoutManager

        mAdapter = TeamFavoriteAdapter(this.context, favorites as ArrayList<TeamFavorite>)
        listTeam.adapter = mAdapter
        showFavorite()

        swipeRefresh.onRefresh {
            favorites.clear()
        }

        return rootView
    }

    private fun showFavorite() {
        try {

        swipeRefresh.isRefreshing = false
        context?.database?.use {
            val result = select(TeamFavorite.TABLE_NAME)
            val favorite = result.parseList(classParser<TeamFavorite>())
            favorites.addAll(favorite)
            mAdapter.notifyDataSetChanged()
        }
        }catch (e : SQLException) {
            println("=============================" + e.message + e.errorCode)
        }
    }
}
