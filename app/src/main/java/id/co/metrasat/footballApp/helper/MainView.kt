package id.co.metrasat.footballApp.helper

import id.co.metrasat.footballApp.model.event.EventsItem

interface MainView {
    fun showLoading()

    fun hideLoading()

    fun showEventList(data:List<EventsItem>)

    companion object {
     var LEAGUE_ID = ""

    }
}