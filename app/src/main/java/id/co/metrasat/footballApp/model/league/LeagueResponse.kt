package id.co.metrasat.footballApp.model.league

import com.google.gson.annotations.SerializedName

class LeagueResponse (@SerializedName("leagues")
    var leagues: List<LeaguesItem> )
