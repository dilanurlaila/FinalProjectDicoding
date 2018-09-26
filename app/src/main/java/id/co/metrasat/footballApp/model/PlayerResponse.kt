package id.co.metrasat.footballApp.model

import com.google.gson.annotations.SerializedName


data class PlayerResponse(@SerializedName("Player")
                          val Player: List<PlayerItem>
)

