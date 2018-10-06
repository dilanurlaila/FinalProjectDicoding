package id.co.metrasat.footballApp.fragment.fragmentEvent

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.ImageView
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.activity.SearchEvents
import id.co.metrasat.footballApp.adapter.EventPageAdapter
import org.jetbrains.anko.support.v4.startActivity


class FragmentEvent : Fragment() {

    private lateinit var eventPageAdapter : EventPageAdapter
    private lateinit var tabLayout : TabLayout
    private lateinit var viewContainer : ViewPager
    private lateinit var imgView :ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_event, container, false)

        activity?.title = "All Match"
        tabLayout = rootView.findViewById(R.id.tabLayoutMatch)
        viewContainer = rootView.findViewById(R.id.viewPagerMatch)
        imgView = rootView.findViewById(R.id.img_searchEvent)

        tabLayout.addTab(tabLayout.newTab().setText("Last Event"))
        tabLayout.addTab(tabLayout.newTab().setText("Next Event"))
        eventPageAdapter = EventPageAdapter(fragmentManager, tabLayout.tabCount)
        viewContainer.adapter = eventPageAdapter
        viewContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        setOnclick()
        return rootView

    }

   private fun setOnclick () {
       imgView.setOnClickListener {
           startActivity<SearchEvents>()
       }
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

