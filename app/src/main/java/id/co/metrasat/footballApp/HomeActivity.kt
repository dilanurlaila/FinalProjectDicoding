package id.co.metrasat.footballApp

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import id.co.metrasat.footballApp.adapter.FavoritePageAdapter
import id.co.metrasat.footballApp.adapter.PageAdapter
import id.co.metrasat.footballApp.fragment.FragmentMatch.FragmentMatch
import id.co.metrasat.footballApp.fragment.FragmentTeams
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.LeagueView
import id.co.metrasat.footballApp.helper.MainView
import id.co.metrasat.footballApp.model.LeaguesItem
import id.co.metrasat.footballApp.presenter.LeaguePresenter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.ctx

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, LeagueView {

    private lateinit var pageAdapter: PageAdapter
    private lateinit var presenter: LeaguePresenter
    private lateinit var favoriteAdapter: FavoritePageAdapter
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbars)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbars, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

//        tabLayoutMainActivity.addTab(tabLayoutMainActivity.newTab())
//        tabLayoutMainActivity.addTab(tabLayoutMainActivity.newTab())
//        pageAdapter = PageAdapter(supportFragmentManager, tabLayoutMainActivity.tabCount, MainView.LEAGUE_ID)
//        viewPagerMain.adapter = pageAdapter
//        viewPagerMain.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutMainActivity))
        addFragment(FragmentMatch())
        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = LeaguePresenter(this, apiRepository, gson)
        presenter.listLeague()
        spinner = findViewById(R.id.spLeagueId)


        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_nextEvent -> {
                addFragment(FragmentMatch())
            }
            R.id.nav_favorite -> {
//                tabLayoutMainActivity.getTabAt(0)?.setText("Event")
//                tabLayoutMainActivity.getTabAt(1)?.setText("Team")
//                favoriteAdapter = FavoritePageAdapter(supportFragmentManager, tabLayoutMainActivity.tabCount)
//                viewPagerMain.adapter = favoriteAdapter
//                viewPagerMain.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutMainActivity))
//                supportActionBar?.title = "Favorite"
//                onBackPressed()
            }
            R.id.nav_teams -> {
                val fragment = FragmentTeams.newInstance()
                addFragment(fragment)
                onBackPressed()
                return true

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.viewMain, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

    override fun showLeagueList(data: List<LeaguesItem>) {
        val arraySpinner: MutableList<String> = mutableListOf()
        for (index in data.indices) {
            if (index == 22) {
                break
            }
            data[index].strLeague.let { arraySpinner.add(index, it) }
        }
        val arrayAdapter = ArrayAdapter(this, R.layout.spinner_custom, arraySpinner)
        spinner.setPopupBackgroundResource(R.color.backgroundSoft)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                MainView.LEAGUE_ID = data[spinner.selectedItemPosition].idLeague
                pageAdapter = PageAdapter(supportFragmentManager, tabLayoutMainActivity.tabCount, MainView.LEAGUE_ID)
                ViewPagerMain.adapter = pageAdapter
                ViewPagerMain.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutMainActivity))

            }
        }

    }
}


