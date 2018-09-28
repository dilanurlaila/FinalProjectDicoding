package id.co.metrasat.footballApp.fragment.FragmentMatch

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.FavoritePageAdapter
import id.co.metrasat.footballApp.adapter.PageAdapter
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.LeagueView
import id.co.metrasat.footballApp.model.LeaguesItem
import id.co.metrasat.footballApp.presenter.LeaguePresenter


class FragmentMatch : Fragment(), LeagueView {

    private lateinit var pageAdapter: PageAdapter
    private lateinit var presenter: LeaguePresenter
    private lateinit var favoriteAdapter: FavoritePageAdapter
    private lateinit var spinner: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_match, container, false)

        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = LeaguePresenter(this, apiRepository, gson)
        presenter.listLeague()

        spinner = rootView.findViewById(R.id.spLeagueId)

        return rootView

    }


    companion object {
        @JvmStatic
        fun newInstance() =
                FragmentMatch().apply {
                    arguments = Bundle().apply {


                    }
                }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLeagueList(data: List<LeaguesItem>) {
    }
}

