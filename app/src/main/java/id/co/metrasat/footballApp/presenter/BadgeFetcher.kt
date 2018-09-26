package id.co.metrasat.footballApp.presenter

import android.widget.ImageView
import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository

class BadgeFetcher {
    private lateinit var presenter : BadgePresenter
    val apiRepository = ApiRepository()

    fun loadLogo (id: String, img:ImageView){
        img.setImageDrawable(null)
        val gson = Gson()
        presenter = BadgePresenter(img, apiRepository, gson)
        presenter.getLogo(id)
    }
}