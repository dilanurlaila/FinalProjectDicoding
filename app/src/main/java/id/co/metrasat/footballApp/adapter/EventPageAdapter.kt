package id.co.metrasat.footballApp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import id.co.metrasat.footballApp.fragment.fragmentEvent.FragmentEventNext
import id.co.metrasat.footballApp.fragment.fragmentEvent.FragmentEventPast


class EventPageAdapter (fm : FragmentManager?, private val noOfTabs:Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                FragmentEventPast()
            }
            else -> {
                FragmentEventNext()
            }
        }
    }

    override fun getCount(): Int= noOfTabs
}