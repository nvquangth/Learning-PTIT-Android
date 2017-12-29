package vn.svptit.learning.activity

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.quangnv.baseproject.fragment.SubjectsFragment
import kotlinx.android.synthetic.main.activity_main.*
import vn.svptit.learning.R

/**
 * Created by NVQuang on 19/12/2017.
 */
class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavigationView()

        onOpenFragment(SubjectsFragment(), false)

    }

    fun setNavigationView() {
        nav_view.itemIconTintList = null
    }

    fun setUpWithToolbar(toolbar: Toolbar) {
        var toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.setDrawerListener(toggle);
        toggle.syncState();
    }

    fun onOpenFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        if (isAddToBackStack) {
            fragmentTransaction.replace(R.id.container, fragment).addToBackStack(null)
        } else {
            fragmentTransaction.replace(R.id.container, fragment)
        }
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        var fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
            return
        }
        if (doubleBackToExitPressedOnce || fragmentManager.backStackEntryCount != 0) {
            super.onBackPressed()
        }
        doubleBackToExitPressedOnce = true

        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}