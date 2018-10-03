package id.co.metrasat.footballApp.fragment.fragmentFavorite

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.FavoritePageAdapter

class FragmentFavorite : Fragment() {

    private lateinit var pageAdapter: FavoritePageAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewContainer : ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        activity?.title = "All Favorite"
        tabLayout = rootView.findViewById(R.id.tabLayoutFavorite)
        viewContainer = rootView.findViewById(R.id.viewPagerFavorite)

       tabLayout.addTab(tabLayout.newTab().setText("Events"))
        tabLayout.addTab(tabLayout.newTab().setText("Teams"))
        pageAdapter = FavoritePageAdapter(fragmentManager, tabLayout.tabCount)
        viewContainer.adapter = pageAdapter
        viewContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        return rootView
    }

}
