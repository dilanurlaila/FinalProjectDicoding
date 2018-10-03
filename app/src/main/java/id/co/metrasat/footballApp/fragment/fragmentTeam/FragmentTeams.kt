package id.co.metrasat.footballApp.fragment.fragmentTeam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.adapter.TeamAdapter
import id.co.metrasat.footballApp.helper.TeamView
import id.co.metrasat.footballApp.helper.invisible
import id.co.metrasat.footballApp.helper.visible
import id.co.metrasat.footballApp.helper.MainView
import id.co.metrasat.footballApp.model.team.TeamsItem
import id.co.metrasat.footballApp.presenter.TeamPresenter
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh


class FragmentTeams : Fragment() , TeamView{

    private var listTeams: MutableList<TeamsItem> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var mAdapter: TeamAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_teams, container, false)
        activity?.title = ("All Team")
        progressBar = rootView.findViewById(R.id.pgBarTeam)
        swipeRefresh = rootView.findViewById(R.id.swipeTeams)
        recyclerView = rootView.findViewById(R.id.rvTeam)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        recyclerView.layoutManager = layoutManager
        mAdapter = TeamAdapter(ctx, listTeams)
        recyclerView.adapter = mAdapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        presenter.getTeamList(MainView.LEAGUE_ID)

        swipeRefresh.onRefresh {
            presenter.getTeamList(MainView.LEAGUE_ID)
        }

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

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<TeamsItem>) {
        swipeRefresh.isRefreshing = false
        listTeams.clear()
        listTeams.addAll(data)
        mAdapter.notifyDataSetChanged()

    }

}
