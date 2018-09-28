package id.co.metrasat.footballApp.helper

import android.widget.ArrayAdapter
import id.co.metrasat.footballApp.fragment.FragmentMatch.FragmentMatch
import id.co.metrasat.footballApp.model.LeaguesItem

interface LeagueView {
    fun showLoading()

    fun hideLoading()

    fun showLeagueList(data:List<LeaguesItem>)


}