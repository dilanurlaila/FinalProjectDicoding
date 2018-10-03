package id.co.metrasat.footballApp.fragment.fragmentEvent

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.EventPageAdapter


class FragmentEvent : Fragment() {

    private lateinit var eventPageAdapter : EventPageAdapter
    private lateinit var tabLayout : TabLayout
    private lateinit var viewContainer : ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_event, container, false)

        activity?.title = "All Match"
        tabLayout = rootView.findViewById(R.id.tabLayoutMatch)
        viewContainer = rootView.findViewById(R.id.viewPagerMatch)

        tabLayout.addTab(tabLayout.newTab().setText("Last Event"))
        tabLayout.addTab(tabLayout.newTab().setText("Next Event"))
        eventPageAdapter = EventPageAdapter(fragmentManager, tabLayout.tabCount)
        viewContainer.adapter = eventPageAdapter
        viewContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        return rootView

    }
    companion object {
        @JvmStatic
        fun newInstance() =
                FragmentEvent().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

}

