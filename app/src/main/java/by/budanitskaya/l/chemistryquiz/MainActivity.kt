package by.budanitskaya.l.chemistryquiz


import androidx.appcompat.app.AppCompatActivity
import by.budanitskaya.l.chemistryquiz.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override fun onStart() {
        super.onStart()
        val navigator = Navigator(supportFragmentManager, binding.navView)
        this.lifecycle.addObserver(navigator)
    }
}