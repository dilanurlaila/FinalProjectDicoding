package id.co.metrasat.footballApp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.util.*
import android.widget.TextView
import android.widget.ProgressBar



class SplashScreen : AppCompatActivity() {
    var mDelay : Handler? = null
    private var splashDelay: Long = 3000
    lateinit var progressBar: ProgressBar
    private val i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        progressBar = findViewById(R.id.pgBarSplash)
        progressBar.setProgress(0);



    }
   }