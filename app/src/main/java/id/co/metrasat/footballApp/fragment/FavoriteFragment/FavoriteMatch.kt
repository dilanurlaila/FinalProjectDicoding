package id.co.metrasat.footballApp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import id.co.metrasat.footballApp.adapter.MatchFavoriteAdapter
import id.co.metrasat.footballApp.database.database
import id.co.metrasat.footballApp.model.EventFavorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh


class FavoriteMatch : Fragment() {

    private var favorites: MutableList<EventFavorite> = mutableListOf()
    private lateinit var listEvent: RecyclerView
    private lateinit var mAdapter: MatchFavoriteAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_matchs, container, false)

        progressBar = rootView.findViewById(R.id.pgBarFavorite)
        listEvent = rootView.findViewById(R.id.rvFavorite)
        swipeRefresh = rootView.findViewById(R.id.swipeFavorite)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        listEvent.layoutManager = layoutManager

        mAdapter = MatchFavoriteAdapter(this.context, favorites as ArrayList<EventFavorite>)
        listEvent.adapter = mAdapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
        }

        return rootView
    }

    private fun showFavorite() {
        swipeRefresh.isRefreshing = false
        context?.database?.use {
            val result = select(EventFavorite.TABLE_NAME)
            val favorite = result.parseList(classParser<EventFavorite>())
            favorites.addAll(favorite)
            mAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FavoriteMatch().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
