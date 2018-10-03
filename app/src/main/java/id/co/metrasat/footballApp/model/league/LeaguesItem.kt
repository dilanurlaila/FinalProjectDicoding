package id.co.metrasat.footballApp.model.league

import com.google.gson.annotations.SerializedName

class LeaguesItem (@SerializedName("strLeagueAlternate")
    val strLeagueAlternate: String = "",

    @SerializedName("strLeague")
    val strLeague: String = "",

    @SerializedName("strSport")
    val strSport: String = "",

    @SerializedName("idLeague")
    val idLeague: String = ""

)