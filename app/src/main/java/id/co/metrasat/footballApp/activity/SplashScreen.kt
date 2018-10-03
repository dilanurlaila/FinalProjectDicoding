package id.co.metrasat.footballApp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import id.co.metrasat.footballApp.R


class SplashScreen : AppCompatActivity() {
    var mDelay : Handler? = null
    private var splashDelay: Long = 3000
    lateinit var progressBar: ProgressBar
    private val i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



    }
   }