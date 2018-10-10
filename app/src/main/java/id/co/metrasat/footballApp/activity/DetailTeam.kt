package id.co.metrasat.footballApp.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.DetailPageAdapter
import id.co.metrasat.footballApp.database.database
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.TeamView
import id.co.metrasat.footballApp.helper.invisible
import id.co.metrasat.footballApp.helper.visible
import id.co.metrasat.footballApp.model.team.TeamFavorite
import id.co.metrasat.footballApp.model.team.TeamsItem
import id.co.metrasat.footballApp.presenter.BadgeFetcher
import id.co.metrasat.footballApp.presenter.TeamPresenter
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class DetailTeam : AppCompatActivity(), TeamView {

    var idTeam = ""
    var nameTeam = ""
    var yearTeam = ""

    private lateinit var progressBar: ProgressBar
    private lateinit var teams: TeamsItem
    private lateinit var presenter: TeamPresenter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewContainer: ViewPager
    private lateinit var detailPageAdapter: DetailPageAdapter
    private lateinit var swipeRefresh :SwipeRefreshLayout

    companion object {
        const val ID_TEAM = "id_team"
        const val NAME_TEAM = "name_team"
        const val YEAR_TEAM = "year_team"
        var teamId = "team_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = null

        tabLayout = findViewById(R.id.tabLayoutDetail)
        viewContainer = findViewById(R.id.viewDetailTeam)
        progressBar = findViewById(R.id.pgBarDetailTeam)
        swipeRefresh = findViewById(R.id.swipeDetailTeam)

        showTab()

        val intent = intent
        idTeam = intent.getStringExtra(ID_TEAM)
        nameTeam = intent.getStringExtra(NAME_TEAM)
        yearTeam = intent.getStringExtra(YEAR_TEAM)
        teamId = intent.getStringExtra(ID_TEAM)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, apiRepository, gson)
        presenter.getDetailTeam(idTeam)

        swipeRefresh.onRefresh {
            presenter.getDetailTeam(idTeam)
        }

        txt_clubName.text = nameTeam
        BadgeFetcher().loadLogo(idTeam, img_club)

        favoriteState()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<TeamsItem>) {
        swipeRefresh.isRefreshing = false
        teams = data[0]
        txt_formedYear.text = teams.intFormedYear

    }

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(TeamFavorite.TABLE_NAME)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to idTeam)
            val favorite = result.parseList(classParser<TeamFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(TeamFavorite.TABLE_NAME,
                        TeamFavorite.TEAM_ID to teams.idTeam,
                        TeamFavorite.TEAM_BADGE to teams.strTeamLogo,
                        TeamFavorite.TEAM_NAME to teams.strTeam,
                        TeamFavorite.TEAM_YEAR to teams.intFormedYear
                )
            }
            snackbar(progressBar, "Added to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(progressBar, e.localizedMessage).show()
        }
    }

    private fun removeFavorite() {
        try {
            database.use {
                delete(TeamFavorite.TABLE_NAME,
                        "(TEAM_ID = {id})",
                        "id" to idTeam)
            }
            snackbar(progressBar, "Removed to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(progressBar, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }


    private fun showTab() {
        tabLayout.addTab(tabLayout.newTab().setText("Overview"))
        tabLayout.addTab(tabLayout.newTab().setText("Players"))
        detailPageAdapter = DetailPageAdapter(supportFragmentManager, tabLayout.tabCount)
        viewContainer.adapter = detailPageAdapter
        viewContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

    }
}