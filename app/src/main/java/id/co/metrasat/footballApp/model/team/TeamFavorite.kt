package id.co.metrasat.footballApp.model.team

data  class TeamFavorite (val id: Long? = null,
                          val teamIdFavorite: String? = null,
                          val teamBadgeFavorite: String?=null,
                          val teamNameFavorite: String?=null,
                          val teamYearFavorite:String?=null
                          ) {

    companion object {
      const  val TABLE_NAME:String = "TABLE_NAME_TEAM"
        const val   ID:String = "ID"
        const val   TEAM_ID = "TEAM_ID"
        const val   TEAM_BADGE:String = "TEAM_BADGE"
        const val   TEAM_NAME:String = "TEAM_NAME"
        const val   TEAM_YEAR:String = "TEAM_YEAR"

    }
}