package id.co.metrasat.footballApp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.FrameLayout
import id.co.metrasat.footballApp.fragment.FragmentMatch.FragmentEventNext
import id.co.metrasat.footballApp.fragment.FragmentMatch.FragmentEventPast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private var content: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        content = findViewById(R.id.container)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        setSupportActionBar(toolbarMainActivity)
        supportActionBar?.title = "Previous Event Match"
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = FragmentEventPast.newInstance()
        addFrgment(fragment)
    }



    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.prev_match -> {
                    val fragment = FragmentEventPast.newInstance()
                    addFrgment(fragment)
                   supportActionBar?.title = "Previous Event Match"
                    return true
                }
                R.id.next_match -> {
                    val fragment = FragmentEventNext.newInstance()
                    addFrgment(fragment)
                    supportActionBar?.title = "Next Event Match"
                    return true
                }
                R.id.favorite -> {
                    val fragment = FavoriteMatch.newInstance()
                    addFrgment(fragment)
                    supportActionBar?.title = "Favorite Event Match"
                    return true
                }

            }
            return false
        }
    }

    @SuppressLint("PrivateResource")
    private fun addFrgment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

}
