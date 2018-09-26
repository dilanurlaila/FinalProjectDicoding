package id.co.metrasat.footballApp.model

data  class TeamFavorite (val id: String? = null,
                          val teamIdFavorite: String? = null,
                          val teamNameFavorite: String?=null,
                          val teambadgeFavorite: String?=null
                          ) {

    companion object {
      const  val TABLE_NAME:String = "TABLE_NAME_TEAM"
        const val   ID:String = "ID"
        const val   TEAM_ID = "TEAM_ID"
        const val   TEAM_NAME:String = "TEAM_NAME"
        const val   TEAM_BADGE:String = "TEAM_BADGE"



    }
}