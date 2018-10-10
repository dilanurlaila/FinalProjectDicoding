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
import id.co.metrasat.footballApp.adapter.EventsAdapter
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.SearchEventView
import id.co.metrasat.footballApp.helper.invisible
import id.co.metrasat.footballApp.helper.visible
import id.co.metrasat.footballApp.model.event.EventsItem
import id.co.metrasat.footballApp.presenter.SearchEventPresenter
import kotlinx.android.synthetic.main.activity_search_event.*

class SearchEvents : AppCompatActivity(), SearchEventView {


    private lateinit var presenter : SearchEventPresenter
    private lateinit var pgBar : ProgressBar
    private lateinit var listSearchEvent :RecyclerView
    private lateinit var eventAdapter : EventsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_event)
        setSupportActionBar(toolbarSearch)

        listSearchEvent = findViewById(R.id.rv_SearchViewEvent)
        pgBar = findViewById(R.id.pgBar_searchEvent)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = SearchEventPresenter(this, apiRepository, gson)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.search)

        val searchView : SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(query: String): Boolean {
                if (!query.isEmpty()) {
                    presenter.searchEvent(query)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()){
                    presenter.searchEvent(query)
                }
                return false
            }
        })
        searchView.requestFocus()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId) {
            R.id.search -> return true
            else ->  super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        pgBar.visible()

    }

    override fun hideLoading() {
        pgBar.invisible()
    }

    override fun showSearchList(data: List<EventsItem>?) {
        val filter: MutableList<EventsItem> = mutableListOf()
        for (index in data?.indices!!) {
            if (data[index].strSport.equals("Soccer")) {
                filter.add(data[index])
            }
        }
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        listSearchEvent.layoutManager = layoutManager
        listSearchEvent.itemAnimator = DefaultItemAnimator()
        eventAdapter = EventsAdapter(this, filter)
        listSearchEvent.adapter = eventAdapter
    }
}
