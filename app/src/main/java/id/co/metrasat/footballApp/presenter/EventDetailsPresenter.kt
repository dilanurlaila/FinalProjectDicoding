package id.co.metrasat.footballApp.presenter

import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.MainView
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.event.EventsResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import kotlin.coroutines.experimental.CoroutineContext

class EventDetailsPresenter(private val view: MainView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson, private val context: CoroutineContextProvider= CoroutineContextProvider()) {

    fun getLookUpEvents(eventId: String?){
        view.showLoading()
        async (context.main ) {
            val data = bg{
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLookUpEvent(eventId)),
                    EventsResponse::class.java)
        }
                view.showEventList(data.await().Events)
                view.hideLoading()
            }
        }


    fun getTeamDetail (eventId : String){
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getLookUpEvent(eventId)),
                        EventsResponse::class.java)
            }
            view.showEventList(data.await().Events)
            view.hideLoading()
        }
    }

    open class CoroutineContextProvider{
        open val main: CoroutineContext by lazy { UI }
    }
}

