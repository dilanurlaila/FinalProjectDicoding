package id.co.metrasat.footballApp.model

import com.google.gson.annotations.SerializedName


data class EventsItem(
        @SerializedName("idEvent")
        var idEvent: String?=null,

        @SerializedName("idLeague")
        val idLeague: String?=null,

        @SerializedName("idAwayTeam")
        var idAwayTeam: String?=null,

        @SerializedName("intHomeScore")
        val intHomeScore: String?=null,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String?=null,

        @SerializedName("intAwayScore")
        val intAwayScore: String?=null,

        @SerializedName("dateEvent")
        val dateEvent: String?=null,

        @SerializedName("strAwayTeam")
        val strAwayTeam: String?=null,

        @SerializedName("strHomeTeam")
        val strHomeTeam: String?=null,

        @SerializedName("strHomeLineupDefense")
        val strHomeLineupDefense: String?=null,

        @SerializedName("strAwayLineupDefense")
        val strAwayLineupDefense: String?=null,

        @SerializedName("strHomeLineupForward")
        val strHomeLineupForward: String?=null,

        @SerializedName("strHomeLineupSubstitutes")
        val strHomeLineupSubtitutes: String?=null,

        @SerializedName("strAwayLineupForward")
        val strAwayLineupForward: String?=null,

        @SerializedName("strAwayLineupSubstitutes")
        val strAwayLineupSubtitutes: String?=null,

        @SerializedName("strAwayLineupMidfield")
        val strAwayLineupMidfield: String?=null,

        @SerializedName("strHomeLineupMidfield")
        val strHomeLineupMidfield: String?=null,

        @SerializedName("strHomeGoalDetails")
        val strHomeGoalDetails: String?=null,

        @SerializedName("strAwayGoalDetails")
        val strAwayGoalsDetails: String?=null,

        @SerializedName("strAwayFormation")
        val strAwayFormation: String?=null,

        @SerializedName("strHomeFormation")
        val strHomeFormation: String? = null

)