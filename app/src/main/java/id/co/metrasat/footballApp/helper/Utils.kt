package id.co.metrasat.footballApp.helper

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible(){
    visibility = View.VISIBLE
}
fun View.invisible(){
    visibility = View.INVISIBLE
}

@SuppressLint("SimpleDateFormat")
fun toSimpleString(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}