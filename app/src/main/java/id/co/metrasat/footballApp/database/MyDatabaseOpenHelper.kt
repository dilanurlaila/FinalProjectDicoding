package id.co.metrasat.footballApp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.co.metrasat.footballApp.model.EventFavorite
import id.co.metrasat.footballApp.model.TeamFavorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx,
        "FavoriteMatch.db", null, 3) {


    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(EventFavorite.TABLE_NAME, true,
                EventFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
                EventFavorite.ID_EVENT to TEXT ,
                EventFavorite.DATE_EVENT to TEXT,
                EventFavorite.ID_HOME to TEXT,
                EventFavorite.HOME_SCORE to TEXT,
                EventFavorite.HOME_NAME to TEXT,
                EventFavorite.ID_AWAY to TEXT,
                EventFavorite.AWAY_SCORE to TEXT,
                EventFavorite.AWAY_NAME to TEXT)

        db.createTable(TeamFavorite.TABLE_NAME, true,
                TeamFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
                TeamFavorite.TEAM_ID to TEXT,
                TeamFavorite.TEAM_NAME to TEXT,
                TeamFavorite.TEAM_BADGE to TEXT
                )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(EventFavorite.TABLE_NAME, true)
        db.dropTable(TeamFavorite.TABLE_NAME, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

