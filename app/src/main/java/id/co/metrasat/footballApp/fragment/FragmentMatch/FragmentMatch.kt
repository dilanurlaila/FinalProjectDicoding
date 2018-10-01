package id.co.metrasat.footballApp.fragment.FragmentMatch

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*

import id.co.metrasat.footballApp.helper.MainView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toolbar
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.PageAdapter
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.LeagueView
import id.co.metrasat.footballApp.model.LeaguesItem
import id.co.metrasat.footballApp.presenter.LeaguePresenter
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.ctx


class FragmentMatch : Fragment(), LeagueView {

    private lateinit var pageAdapter : PageAdapter
    private lateinit var presenter: LeaguePresenter
    private lateinit var spinner: Spinner
    private lateinit var tabLayout : TabLayout
    private lateinit var viewContainer : ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_match, container, false)

        activity?.title = "All Match"
        tabLayout = rootView.findViewById(R.id.tabLayoutMainActivity)
        viewContainer = rootView.findViewById(R.id.ViewPagerMain)

        tabLayout.addTab(tabLayout.newTab().setText("Last Event"))
        tabLayout.addTab(tabLayout.newTab().setText("Next Event"))
        pageAdapter = PageAdapter(fragmentManager, tabLayout.tabCount, MainView.LEAGUE_ID)
        viewContainer.adapter = pageAdapter
        viewContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = LeaguePresenter(this, apiRepository, gson)
        presenter.listLeague()


        return rootView

    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FragmentEventPast().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun showLeagueList(data: List<LeaguesItem>) {
    }
}

