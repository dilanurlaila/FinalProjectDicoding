package id.co.metrasat.footballApp.helper

import id.co.metrasat.footballApp.model.team.TeamsItem

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data:List<TeamsItem>)

    companion object {
         var idTeam =""
    }

}