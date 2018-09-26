package id.co.metrasat.footballApp.model

import com.google.gson.annotations.SerializedName

class LeaguesItem (@SerializedName("strLeagueAlternate")
    var strLeagueAlternate: String = "",

    @SerializedName("strLeague")
    var strLeague: String = "",

    @SerializedName("strSport")
    var strSport: String = "",

    @SerializedName("idLeague")
    var idLeague: String = ""

)