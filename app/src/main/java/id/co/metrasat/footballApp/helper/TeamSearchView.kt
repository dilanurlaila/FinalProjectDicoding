package id.co.metrasat.footballApp.helper

import id.co.metrasat.footballApp.model.team.TeamsItem

interface TeamSearchView {
    fun hideLoading ()
    fun showLoading ()
    fun showSearchList(data:List<TeamsItem>)
}