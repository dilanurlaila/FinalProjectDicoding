package id.co.metrasat.footballApp.helper

import id.co.metrasat.footballApp.model.player.PlayerItem

interface PlayerView{
    fun showPlayerList(data:List<PlayerItem>)
}