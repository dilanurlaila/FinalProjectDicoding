package id.co.metrasat.footballApp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import id.co.metrasat.footballApp.fragment.fragmentFavorite.FavoriteEvent
import id.co.metrasat.footballApp.fragment.fragmentFavorite.FavoriteTeam

class FavoritePageAdapter (fm: FragmentManager?, private val noOfTabs : Int) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
       return when (position) {
            0 -> {
                FavoriteEvent()
            }
            else -> {
                FavoriteTeam()
            }
        }
    }

    override fun getCount(): Int = noOfTabs

}