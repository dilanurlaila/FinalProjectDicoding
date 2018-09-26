package id.co.metrasat.footballApp.helper

import id.co.metrasat.footballApp.model.LeaguesItem

interface LeagueView {
    fun showLoading()

    fun hideLoading()

    fun showLeagueList(data:List<LeaguesItem>)


}