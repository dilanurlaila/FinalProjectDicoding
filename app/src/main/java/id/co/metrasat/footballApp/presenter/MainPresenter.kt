package id.co.metrasat.footballApp.presenter

import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.MainView
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.EventsResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.coroutines.experimental.CoroutineContext


class MainPresenter(private val view: MainView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getEventNext(leagueId: String?) {
        view.showLoading()
        async (context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventNext(leagueId)),
                        EventsResponse::class.java
                )
            }
            view.hideLoading()
                    view.showEventList(data.await().Events)
                }
            }

    fun getPastEvent(leagueId: String?) {
        view.showLoading()
        async (context.main) {
            val data =bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventPast(leagueId)),
                    EventsResponse::class.java
            )}
                view.hideLoading()
                view.showEventList(data.await().Events)
            }
        }
    }


    open class CoroutineContextProvider{
        open val main: CoroutineContext by lazy { UI }
    }
