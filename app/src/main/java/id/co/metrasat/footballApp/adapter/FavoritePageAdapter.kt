package id.co.metrasat.footballApp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import id.co.metrasat.footballApp.FavoriteMatch
import id.co.metrasat.footballApp.fragment.FavoriteFragment.FavoriteTeam

class FavoritePageAdapter (fm: FragmentManager?, private val noOfTabs : Int) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
       return when (position) {
            0 -> {
                FavoriteMatch()
            }
            else -> {
                FavoriteTeam()
            }
        }
    }

    override fun getCount(): Int = noOfTabs

}