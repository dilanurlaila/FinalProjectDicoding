package id.co.metrasat.footballApp.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import id.co.metrasat.footballApp.fragment.FragmentEventNext
import id.co.metrasat.footballApp.fragment.FragmentEventPast


class PageAdapter (fm : FragmentManager?, private val noOfTabs:Int, private val idLeague:String?) : FragmentStatePagerAdapter(fm) {

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