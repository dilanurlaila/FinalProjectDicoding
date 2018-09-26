package id.co.metrasat.footballApp.helper

import java.net.URL

class ApiRepository {
    fun doRequest(url: String): String {
        return  URL(url).readText()
    }
}