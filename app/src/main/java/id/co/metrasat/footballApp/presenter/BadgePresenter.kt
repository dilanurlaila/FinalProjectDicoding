package id.co.metrasat.footballApp.presenter

import android.widget.ImageView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.team.TeamResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import kotlin.coroutines.experimental.CoroutineContext

class BadgePresenter(private val view: ImageView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLogo(teamId: String?) {
        async(contextPool.main) {
            val data = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getLookupTeam(teamId)),
                            TeamResponse::class.java
                    )
                }
                Picasso.get().load(data.await().teams[0].strTeamBadge).into(view)
            }
    }




    open class CoroutineContextProvider{
        open val main: CoroutineContext by lazy { UI }
    }
}
