package by.budanitskaya.l.chemistryquiz


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.budanitskaya.l.chemistryquiz.databinding.ActivityMainBinding
import by.budanitskaya.l.chemistryquiz.firebase.FirebaseAuthHelperImpl
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding
import com.firebase.ui.auth.AuthUI


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    private val firebaseAuthHelper = FirebaseAuthHelperImpl(this)

    val navigator: Navigator by lazy {
        Navigator(supportFragmentManager, binding.navView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ChemistryApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.toolbar)
        title = ""
        this.lifecycle.addObserver(navigator)
    }


    fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
                this.finish()
            }
    }

    override fun onResume() {
        super.onResume()
        firebaseAuthHelper.updateView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}