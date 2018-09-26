package id.co.metrasat.footballApp.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Spinner

import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.EventsAdapter
import id.co.metrasat.footballApp.model.EventsItem
import id.co.metrasat.footballApp.model.TeamsItem
import id.co.metrasat.footballApp.presenter.MainPresenter


class FragmentTeams : Fragment() {
    private var eventsNext: MutableList<TeamsItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var mAdapter: EventsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_teams, container, false)
        progressBar = rootView.findViewById(R.id.pgBarTeam)
        swipeRefresh = rootView.findViewById(R.id.swipeTeams)
        recyclerView =rootView.findViewById(R.id.rvTeam)

        return rootView

    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FragmentTeams().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
