package id.co.metrasat.footballApp.model.event

data class EventFavorite (val id: Long?,
                          val eventId:String?,
                          val eventDate:String?,
                          val homeId:String?,
                          val homeScore:String?,
                          val homeName:String?,
                          val awayId:String?,
                          val awayScore:String?,
                          val awayName:String?){

    companion object {

        const val TABLE_NAME :String = "TABLE_NAMEE"
        const val ID:String ="ID"
        const val ID_EVENT:String = "ID_EVENT"
        const val DATE_EVENT:String = "DATE_EVENT"
        const val ID_HOME:String ="ID_HOME_"
        const val  HOME_NAME:String = "HOME_NAME"
        const val HOME_SCORE:String = "HOME_SCORE"
        const val ID_AWAY:String = "ID_AWAY"
        const val AWAY_NAME:String = "AWAY_NAME"
        const val AWAY_SCORE:String = "AWAY_SCORE"

    }
}