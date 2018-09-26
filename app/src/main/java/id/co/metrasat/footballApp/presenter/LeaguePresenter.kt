package id.co.metrasat.footballApp.presenter

import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.LeagueView
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.LeagueResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguePresenter (private val view: LeagueView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson) {
    fun listLeague () {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getListLeague()),
                    LeagueResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showLeagueList(data.leagues)
            }
        }

    }


}