package id.co.metrasat.footballApp.model.event

import com.google.gson.annotations.SerializedName

data class SearchEventResponse (@SerializedName("event")
    var event: List<EventsItem>? = null)
