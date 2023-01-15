package by.budanitskaya.l.chemistryquiz

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import by.budanitskaya.l.chemistryquiz.utils.context.ContextExtensions.showToast
import by.budanitskaya.l.chemistryquiz.utils.context.ContextExtensions.start
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) {
                val response = IdpResponse.fromResultIntent(result.data)
                if (response == null) {
                    finish()
                }
                val errorCode = response?.error?.errorCode.toString()

                showToast(errorCode)

                Log.d(getString(R.string.error_code), errorCode)
                return@registerForActivityResult
            }

            start<MainActivity>()
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)

        if (FirebaseAuth.getInstance().currentUser == null) {
            createSignInIntent()
        } else {
            start<MainActivity>()
        }
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build()
        )

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .setLogo(R.drawable.chemistry_logo)
            .setTheme(R.style.AuthTheme)
            .setTosAndPrivacyPolicyUrls(
                getString(R.string.tos_url),
                getString(R.string.privacy_policy)
            )
            .build()

        resultLauncher.launch(intent)

    }
}