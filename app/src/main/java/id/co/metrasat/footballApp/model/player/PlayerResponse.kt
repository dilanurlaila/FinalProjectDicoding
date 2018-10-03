package id.co.metrasat.footballApp.model.player

import com.google.gson.annotations.SerializedName


data class PlayerResponse(@SerializedName("Player")
                          val player: List<PlayerItem>
)

