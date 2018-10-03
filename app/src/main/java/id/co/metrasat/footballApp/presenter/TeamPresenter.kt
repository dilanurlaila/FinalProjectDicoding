package id.co.metrasat.footballApp.presenter

import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.TeamView
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.team.TeamResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import kotlin.coroutines.experimental.CoroutineContext


class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(teamId: String?){
        view.showLoading()
        async (context.main){
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllTeam(teamId)),
                        TeamResponse::class.java)
            }
            view.hideLoading()
            view.showTeamList(data.await().teams)
        }
    }

    fun getDetailTeam (teamId: String?){
        view.showLoading()
        async (context.main ) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLookupTeam(teamId)),
                        TeamResponse::class.java)
            }
            view.hideLoading()
            view.showTeamList(data.await().teams)
        }
    }

    open class CoroutineContextProvider{
        open val main: CoroutineContext by lazy { UI }
    }
}