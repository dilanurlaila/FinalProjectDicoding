package id.co.metrasat.footballApp.presenter

import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.PlayerView
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.player.PlayerResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import kotlin.coroutines.experimental.CoroutineContext

class PlayerPresenter(private val view :PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson:Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getListPlayer (teamId: String?){
        async (context.main ) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllPlayer(teamId)),
                        PlayerResponse::class.java)
            }
            view.showPlayerList(data.await().player)
        }
    }

    open class CoroutineContextProvider{
        open val main: CoroutineContext by lazy { UI }
    }
}