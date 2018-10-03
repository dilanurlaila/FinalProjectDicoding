package id.co.metrasat.footballApp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import id.co.metrasat.footballApp.fragment.fragmentTeam.FragmentOverview
import id.co.metrasat.footballApp.fragment.fragmentTeam.FragmentPlayer

class DetailPageAdapter (fm : FragmentManager?, private val noOfTabs :Int ) : FragmentStatePagerAdapter (fm){
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0-> {
               return FragmentOverview()
            }else -> {
                FragmentPlayer()
            }
        }
    }

    override fun getCount(): Int {
        return noOfTabs
    }

}