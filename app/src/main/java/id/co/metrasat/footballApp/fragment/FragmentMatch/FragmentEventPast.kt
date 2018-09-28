package id.co.metrasat.footballApp.fragment.FragmentMatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.EventsAdapter
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.MainView
import id.co.metrasat.footballApp.helper.invisible
import id.co.metrasat.footballApp.helper.visible
import id.co.metrasat.footballApp.model.EventsItem
import id.co.metrasat.footballApp.presenter.MainPresenter
import org.jetbrains.anko.support.v4.onRefresh


class FragmentEventPast : Fragment(), MainView {

    private var eventsNext: MutableList<EventsItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var mAdapter: EventsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewEvent: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_event_past, container, false)

        progressBar = rootView.findViewById(R.id.pgBarPast)
        swipeRefresh = rootView.findViewById(R.id.swipeEventPast)
        recyclerViewEvent = rootView.findViewById(R.id.rvEventPast)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        recyclerViewEvent.layoutManager = layoutManager
        mAdapter = EventsAdapter(context, eventsNext)
        recyclerViewEvent.adapter = mAdapter


        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, apiRepository, gson)
        presenter.getPastEvent(MainView.LEAGUE_ID)

        swipeRefresh.onRefresh {
            presenter.getEventNext(MainView.LEAGUE_ID)
        }

        return rootView
    }

    companion object {
        fun newInstance(): FragmentEventPast {
            return newInstance()
        }
    }

    override fun showLoading() {

        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<EventsItem>) {
        swipeRefresh.isRefreshing = false
        eventsNext.clear()
        eventsNext.addAll(data)
        mAdapter.notifyDataSetChanged()

    }
}
