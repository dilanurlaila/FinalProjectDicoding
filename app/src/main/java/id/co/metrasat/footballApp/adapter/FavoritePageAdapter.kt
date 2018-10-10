package id.co.metrasat.footballApp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.DialogTitle
import id.co.metrasat.footballApp.fragment.fragmentFavorite.FavoriteEvent
import id.co.metrasat.footballApp.fragment.fragmentFavorite.FavoriteTeam

class FavoritePageAdapter (fm: FragmentManager?) : FragmentStatePagerAdapter(fm){
   private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
    return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFrag (fragment: Fragment, title: String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

}