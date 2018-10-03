package id.co.metrasat.footballApp.fragment.fragmentTeam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.activity.DetailTeam
import com.google.gson.Gson
import id.co.metrasat.footballApp.R
import id.co.metrasat.footballApp.adapter.PlayerAdapter
import id.co.metrasat.footballApp.helper.PlayerView
import id.co.metrasat.footballApp.model.player.PlayerItem
import id.co.metrasat.footballApp.presenter.PlayerPresenter
import org.jetbrains.anko.support.v4.ctx

class FragmentPlayer : Fragment(), PlayerView {

    private  var players: MutableList<PlayerItem> =  mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var mAdapter: PlayerAdapter
    private lateinit var listPlayer: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rooView = inflater.inflate(R.layout.fragment_player, container, false)
        listPlayer = rooView.findViewById(R.id.rv_player)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        listPlayer.layoutManager = layoutManager
        mAdapter = PlayerAdapter(ctx, players)
        listPlayer.adapter = mAdapter

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, apiRepository, gson)
        presenter.getListPlayer(DetailTeam.teamId)

        return rooView
    }

    override fun showPlayerList(data: List<PlayerItem>) {
        players.clear()
        players.addAll(data)
        mAdapter.notifyDataSetChanged()

    }
}
