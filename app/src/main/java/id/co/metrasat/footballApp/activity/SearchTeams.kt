package id.co.metrasat.footballApp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.TeamAdapter
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.TeamView
import id.co.metrasat.footballApp.helper.invisible
import id.co.metrasat.footballApp.helper.visible
import id.co.metrasat.footballApp.model.team.TeamsItem
import id.co.metrasat.footballApp.presenter.SearchTeamPresenter
import kotlinx.android.synthetic.main.activity_search_event.*

class SearchTeams : AppCompatActivity(), TeamView {

    private lateinit var listSearchTeam : RecyclerView
    private lateinit var pgBar : ProgressBar
    private lateinit var presenter : SearchTeamPresenter
    private lateinit var teamAdapter : TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_teams)
        setSupportActionBar(toolbarSearch)

        listSearchTeam = findViewById(R.id.rv_SearchViewTeam)
        pgBar = findViewById(R.id.pgBar_searchTeam)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = SearchTeamPresenter(this, apiRepository, gson)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.search)

        val searchView : SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(query: String): Boolean {
                if (!query.isEmpty()) {
                    presenter.searchTeam(query)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()){
                    presenter.searchTeam(query)
                }
                return false
            }
        })
        searchView.requestFocus()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
      pgBar.visible()
    }

    override fun hideLoading() {
    pgBar.invisible()
    }

    override fun showTeamList(data: List<TeamsItem>) {
        val filter: MutableList<TeamsItem> = mutableListOf()
        for (index in data.indices) {
            if (data[index].strSport.equals("Soccer")) {
                filter.add(data[index])
            }
        }
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        listSearchTeam.layoutManager = layoutManager
        listSearchTeam.itemAnimator = DefaultItemAnimator()
        teamAdapter= TeamAdapter(this, filter)
        listSearchTeam.adapter = teamAdapter
    }
}
