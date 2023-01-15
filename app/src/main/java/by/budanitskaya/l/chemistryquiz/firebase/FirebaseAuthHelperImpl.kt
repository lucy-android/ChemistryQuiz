package by.budanitskaya.l.chemistryquiz.firebase

import android.content.Intent
import by.budanitskaya.l.chemistryquiz.LoginActivity
import by.budanitskaya.l.chemistryquiz.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthHelperImpl(private val activity: MainActivity) : FirebaseAuthHelper {
    private val auth: FirebaseUser? by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    override fun updateView() {
        if (activity.intent != null) {
           createUI()
        } else {
            activity.startActivity(Intent(activity, LoginActivity::class.java))
            activity.finish()
        }
    }

    override fun createUI() {
        val list = auth?.providerData
        var providerData = ""
        list?.let {
            for (provider in list) {
                providerData = providerData + " " + provider.providerId
            }
        }
    }
}