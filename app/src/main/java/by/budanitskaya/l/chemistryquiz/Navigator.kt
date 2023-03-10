package by.budanitskaya.l.chemistryquiz

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import by.budanitskaya.l.chemistryquiz.ui.fragment.test.TestFragment
import by.budanitskaya.l.chemistryquiz.ui.fragment.games.GamesFragment
import by.budanitskaya.l.chemistryquiz.ui.fragment.home.HomeFragment
import by.budanitskaya.l.chemistryquiz.ui.fragment.notifications.NotificationsFragment
import by.budanitskaya.l.chemistryquiz.ui.fragment.result.TestResultFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class Navigator(
    private val fragmentManager: FragmentManager,
    private val navView: BottomNavigationView
) :
    DefaultLifecycleObserver, NavigationBarView.OnItemSelectedListener {

    private var homeFragment: HomeFragment? = null
        get() = if (field == null) HomeFragment() else field

    private var gamesFragment: GamesFragment? = null
        get() = if (field == null) GamesFragment() else field

    private var notificationFragment: NotificationsFragment? = null
        get() = if (field == null) NotificationsFragment() else field

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        loadFragment(homeFragment)
        val navigation = navView
        navigation.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> fragment = homeFragment
            R.id.navigation_games -> fragment = gamesFragment
            R.id.navigation_notifications -> fragment = notificationFragment
        }
        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commit()
            return true
        }
        return false
    }

    fun navigateTo(screen: Screen) {
        when (screen) {
            Screen.TASK_SCREEN -> loadFragment(TestFragment.newInstance())
            Screen.TEST_RESULT_SCREEN -> loadFragment(TestResultFragment.newInstance())
            else -> {
                // do nothing
            }
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        homeFragment = null
        gamesFragment = null
        notificationFragment = null
    }

}

enum class Screen {
    HOME_SCREEN,
    TASK_SCREEN,
    TEST_RESULT_SCREEN
}