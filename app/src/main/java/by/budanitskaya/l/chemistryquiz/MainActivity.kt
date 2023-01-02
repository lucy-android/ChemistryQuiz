package by.budanitskaya.l.chemistryquiz


import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import by.budanitskaya.l.chemistryquiz.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    companion object {
        const val SPLASH_SCREEN_DELAY = 3000L
        const val ANIMATION_DELAY = 1500
    }

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private var contentHasLoaded: AtomicBoolean = AtomicBoolean(false)

    private val firebaseAuthHelper = FirebaseAuthHelperImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as ChemistryApp).appComponent.inject(this)
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        startLoadingContent()

        setupSplashScreen(splashScreen)
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.toolbar)
        title = ""
        val navigator = Navigator(supportFragmentManager, binding.navView)
        this.lifecycle.addObserver(navigator)
    }

    private fun startLoadingContent() {
        Timer().schedule(SPLASH_SCREEN_DELAY) { contentHasLoaded.compareAndSet(false, true) }
    }

    private fun setupSplashScreen(splashScreen: SplashScreen) {
        val content = binding.root
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (contentHasLoaded.get()) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else false
                }
            }
        )

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideBack = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.view.height.toFloat()
            ).apply {
                interpolator = DecelerateInterpolator()
                duration = calculateRemainingAnimationDuration(splashScreenView) + ANIMATION_DELAY
                doOnEnd { splashScreenView.remove() }
            }

            slideBack.start()
        }
    }

    private fun calculateRemainingAnimationDuration(splashScreenView: SplashScreenViewProvider): Long {
        val firstAnimationExpectedDuration = splashScreenView.iconAnimationDurationMillis
        val firstAnimationStart = splashScreenView.iconAnimationStartMillis
        val firstAnimationEnd = System.currentTimeMillis()
        return (firstAnimationExpectedDuration + firstAnimationStart - firstAnimationEnd)
            .coerceAtLeast(0L)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.logout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sign_out -> {
                signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
                this.finish()
                Toast.makeText(this, "Successfully Log Out", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onResume() {
        super.onResume()
        // firebaseAuthHelper.updateView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}

class FirebaseAuthHelperImpl(private val activity: MainActivity) : FirebaseAuthHelper {
    private val auth: FirebaseUser by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    override fun updateView() {
        if (activity.intent != null) {
           // createUI()
        } else {
            activity.startActivity(Intent(activity, LoginActivity::class.java))
            activity.finish()
        }
    }

    override fun createUI() {
        val list = auth.providerData
        var providerData = ""
        list.let {
            for (provider in list) {
                providerData = providerData + " " + provider.providerId
            }
        }
    }
}

interface FirebaseAuthHelper{
    fun updateView()
    fun createUI()
}