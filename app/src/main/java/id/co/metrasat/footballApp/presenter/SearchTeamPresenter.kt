package id.co.metrasat.footballApp.presenter

import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.TeamView
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.team.SearchTeamResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamPresenter (val view : TeamView, val apiRepository: ApiRepository, val gson:Gson,
                           val context : CoroutineContextProvider = CoroutineContextProvider()) {

    fun searchTeam(teamName: String) {
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchTeams(teamName)),
                SearchTeamResponse::class.java)
            }
            view.hideLoading()
            view.showTeamList(data.await().teams)
        }
    }
}