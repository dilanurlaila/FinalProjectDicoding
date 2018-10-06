package id.co.metrasat.footballApp.helper

import id.co.metrasat.footballApp.model.event.EventsItem

interface SearchEventView  {
    fun showLoading()
    fun hideLoading()
    fun showSearchList (data: List<EventsItem>?)
}