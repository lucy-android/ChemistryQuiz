package by.budanitskaya.l.chemistryquiz


import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import by.budanitskaya.l.chemistryquiz.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private var contentHasLoaded: AtomicBoolean = AtomicBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startLoadingContent()

        setupSplashScreen(splashScreen)
    }


    override fun onStart() {
        super.onStart()
        val navigator = Navigator(supportFragmentManager, binding.navView)
        this.lifecycle.addObserver(navigator)
    }

    private fun startLoadingContent() {
        Timer().schedule(3000) { contentHasLoaded.compareAndSet(false, true) }
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
                View.TRANSLATION_X,
                0f,
                -splashScreenView.view.width.toFloat()
            ).apply {
                interpolator = DecelerateInterpolator()
                duration = calculateRemainingAnimationDuration(splashScreenView) + 500
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
}