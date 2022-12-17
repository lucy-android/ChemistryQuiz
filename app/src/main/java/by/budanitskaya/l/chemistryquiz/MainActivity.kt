package by.budanitskaya.l.chemistryquiz


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import by.budanitskaya.l.chemistryquiz.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        super.onCreate(savedInstanceState)
    }


    override fun onStart() {
        super.onStart()
        val navigator = Navigator(supportFragmentManager, binding.navView)
        this.lifecycle.addObserver(navigator)
    }
}