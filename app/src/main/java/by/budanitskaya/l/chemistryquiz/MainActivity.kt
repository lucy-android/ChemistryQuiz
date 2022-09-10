package by.budanitskaya.l.chemistryquiz


import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.databinding.ActivityMainBinding
import by.budanitskaya.l.chemistryquiz.games.GamesFragment
import by.budanitskaya.l.chemistryquiz.home.HomeFragment
import by.budanitskaya.l.chemistryquiz.notifications.NotificationsFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity(R.layout.activity_main), NavigationBarView.OnItemSelectedListener {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private var homeFragment: HomeFragment? = null
        get() = if (field == null) HomeFragment() else field

    private var gamesFragment: GamesFragment? = null
        get() = if (field == null) GamesFragment() else field

    private var notificationFragment: NotificationsFragment? = null
        get() = if (field == null) NotificationsFragment() else field

    override fun onStart() {
        super.onStart()
        loadFragment(HomeFragment())

        val navigation = binding.navView
        navigation.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> fragment = HomeFragment()
            R.id.navigation_games-> fragment = GamesFragment()
            R.id.navigation_notifications -> fragment = NotificationsFragment()
        }
        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        homeFragment = null
        gamesFragment = null
        notificationFragment = null
    }
}