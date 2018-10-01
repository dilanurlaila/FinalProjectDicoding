package id.co.metrasat.footballApp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import id.co.metrasat.footballApp.fragment.FragmentMatch.FragmentEventNext
import id.co.metrasat.footballApp.fragment.FragmentMatch.FragmentEventPast
import id.co.metrasat.footballApp.fragment.FragmentMatch.FragmentMatch
import id.co.metrasat.footballApp.fragment.FragmentTeams
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.toast

class Main2Activity : AppCompatActivity() {

    private var content: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
       // setSupportActionBar(toolbar)

        content = findViewById(R.id.container)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        addFragment(FragmentMatch())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }


    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.match -> {
                    addFragment(FragmentMatch())
                    supportActionBar?.title = "All Match"
                    return true
                }
                R.id.team -> {
                    addFragment(FragmentTeams())
                    supportActionBar?.title = "All Team"
                    return true
                }
                R.id.favorite -> {
                    addFragment(FavoriteMatch())
                    supportActionBar?.title = "All Favorite"
                    return true
                }

            }
            return false
        }
    }

    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

}
