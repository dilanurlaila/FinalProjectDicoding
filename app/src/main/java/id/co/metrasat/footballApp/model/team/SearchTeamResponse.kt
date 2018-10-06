package id.co.metrasat.footballApp.model.team

import com.google.gson.annotations.SerializedName

data class SearchTeamResponse(@SerializedName("teams")
	val teams: List<TeamsItem>
)