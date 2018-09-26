package id.co.metrasat.footballApp.model

import android.view.SearchEvent
import com.google.gson.annotations.SerializedName

data class SearchResponse (@SerializedName("event")
    var event: List<SearchEvent>? = null)
