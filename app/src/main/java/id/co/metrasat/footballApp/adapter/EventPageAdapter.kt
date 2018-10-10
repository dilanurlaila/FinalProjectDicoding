package id.co.metrasat.footballApp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class EventPageAdapter (fm : FragmentManager?) : FragmentStatePagerAdapter(fm) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentListTitlte = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int{
        return mFragmentList.size
    }

    fun addfrag(fragment :Fragment, title: String){
        mFragmentList.add(fragment)
        mFragmentListTitlte.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return  mFragmentListTitlte[position]
    }
}