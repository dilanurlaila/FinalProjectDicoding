package id.co.metrasat.footballApp.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.R.drawable.ic_add_to_favorites
import id.co.metrasat.footballApp.R.drawable.ic_added_to_favorites
import id.co.metrasat.footballApp.R.id.add_to_favorite
import id.co.metrasat.footballApp.R.menu.menu_detail
import id.co.metrasat.footballApp.database.database
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.MainView
import id.co.metrasat.footballApp.helper.invisible
import id.co.metrasat.footballApp.helper.visible
import id.co.metrasat.footballApp.model.event.EventsItem
import id.co.metrasat.footballApp.model.event.EventFavorite
import id.co.metrasat.footballApp.presenter.BadgeFetcher
import id.co.metrasat.footballApp.presenter.EventDetailsPresenter
import kotlinx.android.synthetic.main.detail_activity.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar


class DetailMatch : AppCompatActivity(), MainView {

    var idEvent: String = ""
    var idAway: String = ""
    var idHome: String = ""
    var nameHome: String = ""
    var nameAway: String = ""

    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: EventDetailsPresenter
    private lateinit var events: EventsItem

    companion object {
        const val ID_EVENTS = "id_events"
        const val ID_AWAY = "id_Away"
        const val ID_HOME = "id_Home"
        const val HOME_NAME = "home_name"
        const val AWAY_NAME = "away_name"
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        progressBar = findViewById(R.id.pg_bar)

        val intent = intent
        idEvent = intent.getStringExtra(ID_EVENTS)
        idAway = intent.getStringExtra(ID_AWAY)
        idHome = intent.getStringExtra(ID_HOME)
        nameAway = intent.getStringExtra(AWAY_NAME)
        nameHome = intent.getStringExtra(HOME_NAME)

        val request = ApiRepository()
        val gson = Gson()
        presenter = EventDetailsPresenter(this, request, gson)
        presenter.getLookUpEvents(idEvent)

        favoriteState()

        BadgeFetcher().loadLogo(idHome, img_home)
        BadgeFetcher().loadLogo(idAway, img_away)
    }


    override fun showEventList(data: List<EventsItem>) {
        events = data[0]
        txt_home_name_club.text = nameHome
        txt_away_name_club.text = nameAway

        txt_home_goals.text = events.strHomeGoalDetails?.replace(";", "\n")
        txt_away_goals.text = events.strAwayGoalsDetails?.replace(";", "\n")

        if (events.intHomeScore != null) txt_home_shots.text = events.intHomeScore.toString()
        if (events.intAwayScore != null) txt_away_shots.text = events.intAwayScore.toString()

        txt_home_defense.text = events.strHomeLineupDefense?.replace(";", "\n")
        txt_away_defense.text = events.strAwayLineupDefense?.replace(";", "\n")

        txt_home_formation.text = events.strHomeFormation?.replace(";", "\n")
        txt_away_formation.text = events.strAwayFormation?.replace(";", "\n")

        txt_home_midfield.text = events.strHomeLineupMidfield
        txt_away_midfield.text = events.strAwayLineupMidfield

        txt_away_subtitutes.text = events.strAwayLineupSubtitutes
        txt_home_substitutes.text = events.strHomeLineupSubtitutes

        txt_away_forward.text = events.strAwayLineupForward
        txt_home_forward.text = events.strHomeLineupForward

    }

    override fun showLoading() {
        progressBar.visible()

    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    private var menuItem: Menu? = null
    private var isFavorite:Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_detail, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFavorite() else   addtoFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        database.use{
            val result = select(EventFavorite.TABLE_NAME)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<EventFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addtoFavorite () {
        try {
            database.use {
                insert(EventFavorite.TABLE_NAME,
                        EventFavorite.ID_EVENT to events.idEvent,
                        EventFavorite.DATE_EVENT to events.dateEvent,
                        EventFavorite.ID_HOME to events.idHomeTeam,
                        EventFavorite.HOME_NAME to events.strHomeTeam,
                        EventFavorite.HOME_SCORE to events.intAwayScore,
                        EventFavorite.ID_AWAY to events.idAwayTeam,
                        EventFavorite.AWAY_SCORE to events.intAwayScore,
                        EventFavorite.AWAY_NAME to events.strAwayTeam
                )
            }
            snackbar(progressBar,"Added to Favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(progressBar, e.localizedMessage).show()
        }
    }

    private fun removeFavorite(){
        try {
            database.use {
                delete(EventFavorite.TABLE_NAME,
                        "(ID_EVENT = {id})",
                        "id" to idEvent)
            }
            snackbar(progressBar, "Removed to Favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(progressBar, e.localizedMessage).show()
        }
    }

    private fun setFavorite () {
        if(isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}

