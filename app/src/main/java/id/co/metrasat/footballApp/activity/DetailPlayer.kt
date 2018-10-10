package id.co.metrasat.footballApp.activity

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.squareup.picasso.Picasso
import id.co.metrasat.footballApp.R
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayer : AppCompatActivity() {

    var idPlayer = ""
    var name = ""
    var description = ""
    var position = ""
    var height = ""
    var weight = ""
    var thumb = ""

    companion object {
        const val ID_PLAYER = "id_player"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val POSITION = "position"
        const val HEIGHT = "height"
        const val WEIGHT = "weight"
        const val THUMB = "thumb"

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        setSupportActionBar(toolbarPlayer)
        supportActionBar?.title="Detail Player"

        val intent = intent

        idPlayer = intent.getStringExtra(ID_PLAYER)
        name = intent.getStringExtra(NAME)
        description = intent.getStringExtra(DESCRIPTION)
        height = intent.getStringExtra(HEIGHT)
        thumb = intent.getStringExtra(THUMB)
        weight = intent.getStringExtra(WEIGHT)
        position = intent.getStringExtra(POSITION)

        Picasso.get().load(thumb).into(img_header_player)
        txt_playerName.text = name
        txt_description.text = description
        txt_position.text = position
        txt_height.text = height
        txt_weight.text = weight


    }

}
