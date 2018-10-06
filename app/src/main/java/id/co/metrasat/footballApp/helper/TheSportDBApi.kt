package id.co.metrasat.footballApp.helper

import id.co.metrasat.footballApp.BuildConfig

object TheSportDBApi {

    fun getEventNext(league: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=" + league
    }

    fun getEventPast(leagueId: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=" + leagueId
    }

    fun getSearchEvent(nameEvent :String?) : String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e=" + nameEvent
    }

    fun getListLeague (): String{
        return  "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php"
    }

    fun getLookUpEvent(eventId: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=" + eventId
    }

    fun getAllPlayer(idTeam :String?):String{
    return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id=" + idTeam
    }


    fun getLookupTeam(idTeam: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=" + idTeam
    }

    fun getAllTeam(idTeam:String?):String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_teams.php?id=" + idTeam
    }


    fun getSearchTeams (nameTeam: String):String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t=" + nameTeam
    }
}