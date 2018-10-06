package id.co.metrasat.footballApp.presenter

import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.SearchEventView
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.event.SearchEventResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchEventPresenter (val view: SearchEventView, val apiRepository: ApiRepository, val gson:Gson,
                            val context : CoroutineContextProvider = CoroutineContextProvider()){

    fun searchEvent (eventName : String){
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchEvent(eventName)),
                        SearchEventResponse::class.java )
        }
            view.hideLoading()
            view.showSearchList(data.await().event)



        }

    }
}