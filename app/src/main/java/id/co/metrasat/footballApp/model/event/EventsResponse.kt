package id.co.metrasat.footballApp.model.event

import com.google.gson.annotations.SerializedName

data class EventsResponse(
        @SerializedName("events")
        val Events: List<EventsItem>
)