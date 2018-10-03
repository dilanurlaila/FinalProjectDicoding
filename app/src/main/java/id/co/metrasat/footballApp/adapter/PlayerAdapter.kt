package id.co.metrasat.footballApp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.co.metrasat.footballApp.R.id.*
import org.jetbrains.anko.*
import id.co.metrasat.footballApp.model.player.PlayerItem

class PlayerAdapter (private val context: Context?, private val player: MutableList<PlayerItem>)
    : RecyclerView.Adapter<PlayerAdapter.PlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        return PlayerHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))

    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindItem (player[position])

    }
    override fun getItemCount(): Int = player.size

    class PlayerUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = img_Club
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = txt_NameClub
                        textSize = 16f
                    }.lparams { margin = (dip(16))
                    }

                    textView{
                        id = txt_Formasi
                        textSize = 16f
                    }.lparams{ gravity = right}
                }
            }

        }
    }

    class PlayerHolder (view:View): RecyclerView.ViewHolder (view){
        private val imgClub: ImageView = view.find(img_Club)
        private val txtNameClub :TextView = view.find(txt_NameClub)
        private val txtFormasi :TextView = view.find(txt_Formasi)
        fun bindItem (player :PlayerItem) {
            Picasso.get().load(player.strCutout).into(imgClub)
            txtNameClub.text = player.strPlayer
            txtFormasi.text = player.strPosition


        }

    }



}