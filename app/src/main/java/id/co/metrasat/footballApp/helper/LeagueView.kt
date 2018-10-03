package id.co.metrasat.footballApp.helper

import id.co.metrasat.footballApp.model.league.LeaguesItem

interface LeagueView {
    fun hideLoading()
    fun showLoading ()
    fun showLeagueList(data:List<LeaguesItem>)


}