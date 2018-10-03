package id.co.metrasat.footballApp.fragment.fragmentTeam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.TeamView
import id.co.metrasat.footballApp.model.team.TeamsItem
import id.co.metrasat.footballApp.activity.DetailTeam
import id.co.metrasat.footballApp.presenter.TeamPresenter

class FragmentOverview : Fragment(), TeamView {

    private lateinit var teams : TeamsItem
    private lateinit var presenter : TeamPresenter
    private lateinit var txtOverview : TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
     val rootView = inflater.inflate(R.layout.fragment_overview, container, false)

        txtOverview = rootView.findViewById(R.id.txt_overview)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, apiRepository, gson)
        presenter.getDetailTeam(DetailTeam.teamId)
        return rootView
    }


    override fun showLoading() {


    }

    override fun hideLoading() {

    }

    override fun showTeamList(data: List<TeamsItem>) {
        teams = data[0]
        txtOverview.text = teams.strDescriptionEN

    }

}
